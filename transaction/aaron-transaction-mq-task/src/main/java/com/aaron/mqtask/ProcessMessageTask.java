package com.aaron.mqtask;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.apache.commons.lang.time.DateFormatUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaron.mqclient.TransactionMqRemoteClient;
import com.aaron.mqclient.dto.TransactionMessage;
@Service
public class ProcessMessageTask {
 	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessMessageTask.class);
 	
 	@Autowired
 	private TransactionMqRemoteClient transactionMqRemoteClient;
 	
 	@Autowired
 	private Producer producer;
 	
 	@Autowired
 	private RedissonClient redisson;
 	
 	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
 	
 	private Semaphore semaphore = new Semaphore(20);
 	
 	// 啟動發送消息任務
 	public void start() {
 		Thread th = new Thread(new Runnable() {
 			
 			public void run() {
 				while(true) {
 					// 獲取一個分布式鎖，然後開始執行業務邏輯
 					final RLock lock = redisson.getLock("aaron-transaction-mq-task");
 					try {
 						lock.lock();
 						System.err.println("開始發送消息:" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
 						int sleepTime = process();
 						// 如果沒有要處理的消息，則休眠一段時間
 						if (sleepTime > 0) {
 							Thread.sleep(10000);
 						}
 					} catch (Exception e) {
 						LOGGER.error("", e);
 					} finally {
 						// 釋放分分佈式鎖
 						lock.unlock();
 					}
 				}
 			}
 		});
 		th.start();
 	}
 	
 	/**
 	 *  發送消息
 	 *  從消費服務端獲取 5000條沒有被消費的消息，如果拿到 5000條就證明還有其餘沒有被消費的消息，那就把休眠時間改為0，不進入休眠操作，然後將每條休息發出去，通過 Thread pool 中進行處理。
 	 * @return
 	 * @throws Exception
 	 */
 	private int process() throws Exception {
 		int sleepTime = 10000;	//默認執行完之後等等10秒
 		List<TransactionMessage> messageList = transactionMqRemoteClient.findByWatingMessage(5000);
 		if (messageList.size() == 5000) {
 			sleepTime = 0;
 		}
 		final CountDownLatch latch = new CountDownLatch(messageList.size());
 		for (final TransactionMessage message : messageList) {

 			semaphore.acquire();
 			fixedThreadPool.execute(new Runnable() {
 				
 				public void run() {
 					try {
 						doProcess(message);
 					} catch (Exception e) {
 						LOGGER.error("", e);
 					} finally {
 			 			// seaphore 用於控制處理的速度
 						semaphore.release();
 						// CountDownLatch 保證這一批資料都處理完成之後，才處理下面的邏輯。
 						latch.countDown();
 					}
 				}
 			});
 		}
 		latch.await();
 		return sleepTime;
 	}
 	
 	/**
 	 * 具體發送消息的邏輯
 	 * 首先進行消息的死亡判斷，如果發送次數已經超出了設定的死亡次數，就把這筆消息改成死亡消息，不在進行處理。
 	 * 然後進行發送時間的判斷，沒有超過 1分鐘的消息不進行重新發送。（後許可考慮類似死亡判斷同樣方式，改由配置方式）
 	 * 如果是在一分鐘之外的消息，就在向 MQ 中發送消息同時更新這條消息的發送時間
 	 * @param message
 	 */
 	private void doProcess(TransactionMessage message) {
 		//檢查此消息是否滿足死亡條件
 		if (message.getSendCount() > message.getDieCount()) {
 			transactionMqRemoteClient.confirmDieMessage(message.getId());
 			return;
 		}
 		
 		//距離上次發送時間超過一分鐘才繼續發送
 		long currentTime = System.currentTimeMillis();
 		long sendTime = 0;
 		if (message.getSendDate() != null) {
 			sendTime = message.getSendDate().getTime();
 		}
 		if (currentTime - sendTime > 60000) {
 			System.out.println("發送具體消息：" + message.getId());
 			
 			//向MQ發送消息
 			MessageDto messageDto = new MessageDto();
 			messageDto.setMessageId(message.getId());
 			messageDto.setMessage(message.getMessage());
 			producer.send(message.getQueue(), JsonUtils.toJson(messageDto));
 			
 			//修改消息發送次數以及最近發送時間
 			transactionMqRemoteClient.incrSendCount(message.getId(), DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
 			
 		}
 	}
}
