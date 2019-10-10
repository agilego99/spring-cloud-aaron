package com.aaron.transaction_mq.service;
import java.util.Date;
import java.util.List;
import org.gordianknot.jdbc.PageQueryParam;
import com.aaron.transaction_mq.enums.TransactionMessageStatusEnum;
import com.aaron.transaction_mq.po.TransactionMessage;
/**
 * 消息業務類
 * @author aaron
 *
 */
public interface TransactionMessageService {
 	
 	/**
 	* 發送消息，只存儲到消息表中，發送邏輯有具體的發送線程執行
 	* @param message  消息內容
 	* @return true 成功 | false 失敗
 	*/
 	boolean sendMessage(TransactionMessage message);
 	/**
 	* 批量發送消息，只存儲到消息表中，發送邏輯有具體的發送線程執行
 	* @param message  消息內容
 	* @return true 成功 | false 失敗
 	*/
 	boolean sendMessage(List<TransactionMessage> messages);
 	
 	/**
 	* 確認消息被消費
 	* @param customerSystem  消費的系統
 	* @param messageId	消息ID
 	* @return
 	*/
 	boolean confirmCustomerMessage(String customerSystem, Long messageId);
 	
 	/**
 	* 查詢最早沒有被消費的消息
 	* @param limit	查詢條數
 	* @return
 	*/
 	List<TransactionMessage> findByWatingMessage(int limit);
 	
 	/**
 	* 確認消息死亡
 	* @param messageId 消息ID
 	* @return
 	*/
 	boolean confirmDieMessage(Long messageId);
 	
 	/**
 	* 累加發送次數
 	* @param messageId 消息ID
 	* @param sendDate  發送時間（task服務中的時間，防止服務器之間時間不同步問題）
 	* @return
 	*/
 	boolean incrSendCount(Long messageId, Date sendDate);
 	
 	/**
 	* 重新發送當前已死亡的消息
 	* @return
 	*/
 	boolean retrySendDieMessage();
 	
 	/**
 	* 分頁查詢具體狀態的消息
 	* @param query
 	* @param status
 	* @return
 	*/
 	List<TransactionMessage> findMessageByPage(PageQueryParam query, TransactionMessageStatusEnum status);
}
