package com.aaron.mqclient;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaron.mqclient.dto.TransactionMessage;
import com.aaron.mqclient.query.MessageQuery;
/**
 * 事務消息服務調用客戶端
 * @author aaron
 *
 */
@FeignClient(value = "aaron-transaction-mq-service", path = "/message", fallback = TransactionMqRemoteClientHystrix.class)
public interface TransactionMqRemoteClient {
 	
 	/**
 	* 發送消息，只存儲到消息表中，發送邏輯有具體的發送線程執行
 	* @param message  消息內容
 	* @return true 成功 | false 失敗
 	*/
 	@PostMapping("/send")
 	public boolean sendMessage(@RequestBody TransactionMessage message);
 	/**
 	* 批量發送消息，只存儲到消息表中，發送邏輯有具體的發送線程執行
 	* @param message  消息內容
 	* @return true 成功 | false 失敗
 	*/
 	@PostMapping("/sends")
 	public boolean sendMessage(@RequestBody List<TransactionMessage> messages);
 	
 	/**
 	* 累加發送次數
 	* @param messageId 消息ID
 	* @param sendDate  發送時間（task服務中的時間，防止服務器之間時間不同步問題）
 	* @return
 	*/
 	@PostMapping("/incrSendCount")
 	public boolean incrSendCount(@RequestParam("messageId")Long messageId, @RequestParam("sendDate")String sendDate);
 	/**
 	* 確認消息死亡
 	* @param messageId 消息ID
 	* @return
 	*/
 	@PostMapping("/confirm/die")
 	public boolean confirmDieMessage(@RequestParam("messageId")Long messageId);
 	
 	/**
 	* 確認消息被消費
 	* @param customerSystem  消費的系統
 	* @param messageId	消息ID
 	* @return
 	*/
 	@PostMapping("/confirm/customer")
 	public boolean confirmCustomerMessage(@RequestParam("customerSystem")String customerSystem,
 			@RequestParam("messageId")Long messageId);
 	/**
 	* 查詢最早沒有被消費的消息
 	* @param limit	查詢條數
 	* @return
 	*/
 	@GetMapping("/wating")
 	public List<TransactionMessage> findByWatingMessage(@RequestParam("limit")int limit);
 	/**
 	* 重新發送當前已死亡的消息
 	* @return
 	*/
 	@PostMapping("/send/retry")
 	public boolean retrySendDieMessage();
 	
 	/**
 	* 分頁查詢具體狀態的消息
 	* @param query
 	* @param status
 	* @return
 	*/
 	@PostMapping("/query")
 	public List<TransactionMessage> findMessageByPage(@RequestBody MessageQuery query);
 	
}
