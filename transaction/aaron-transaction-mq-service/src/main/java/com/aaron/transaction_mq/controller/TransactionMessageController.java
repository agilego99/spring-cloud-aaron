package com.aaron.transaction_mq.controller;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.gordianknot.jdbc.util.DateUtils;
import com.aaron.transaction_mq.enums.TransactionMessageStatusEnum;
import com.aaron.transaction_mq.po.TransactionMessage;
import com.aaron.transaction_mq.query.MessageQuery;
import com.aaron.transaction_mq.service.TransactionMessageService;
/**
 * 可靠性消息接口
 * @author aaron
 *
 */
@RestController
@RequestMapping(value="/message")
public class TransactionMessageController {
 	
 	@Autowired
 	private TransactionMessageService transactionMessageService;
 	
 	/**
 	* 發送消息，只存儲到消息表中，發送邏輯有具體的發送線程執行
 	* @param message  消息內容
 	* @return true 成功 | false 失敗
 	*/
 	@PostMapping("/send")
 	public boolean sendMessage(@RequestBody TransactionMessage message) {
 		return transactionMessageService.sendMessage(message);
 	}
 	/**
 	* 批量發送消息，只存儲到消息表中，發送邏輯有具體的發送線程執行
 	* @param message  消息內容
 	* @return true 成功 | false 失敗
 	*/
 	@PostMapping("/sends")
 	public boolean sendMessage(@RequestBody List<TransactionMessage> messages) {
 		return transactionMessageService.sendMessage(messages);
 	}
 	/**
 	* 確認消息被消費
 	* @param customerSystem  消費的系統
 	* @param messageId	消息ID
 	* @return
 	*/
 	@PostMapping("/confirm/customer")
 	public boolean confirmCustomerMessage(@RequestParam("customerSystem")String customerSystem,
 			@RequestParam("messageId")Long messageId) {
 		return transactionMessageService.confirmCustomerMessage(customerSystem, messageId);
 	}
 	/**
 	* 查詢最早沒有被消費的消息
 	* @param limit	查詢條數
 	* @return
 	*/
 	@GetMapping("/wating")
 	public List<TransactionMessage> findByWatingMessage(@RequestParam("limit")int limit) {
 		return transactionMessageService.findByWatingMessage(limit);
 	}
 	/**
 	* 確認消息死亡
 	* @param messageId 消息ID
 	* @return
 	*/
 	@PostMapping("/confirm/die")
 	public boolean confirmDieMessage(@RequestParam("messageId")Long messageId) {
 		return transactionMessageService.confirmDieMessage(messageId);
 	}
 	/**
 	* 累加發送次數
 	* @param messageId 消息ID
 	* @param sendDate  發送時間（task服務中的時間，防止服務器之間時間不同步問題）
 	* @return
 	*/
 	@PostMapping("/incrSendCount")
 	public boolean incrSendCount(@RequestParam("messageId")Long messageId, @RequestParam("sendDate")String sendDate) {
 		try {
 			if (StringUtils.isBlank(sendDate)) {
 				return transactionMessageService.incrSendCount(messageId, new Date());
 			} else {
 				return transactionMessageService.incrSendCount(messageId, DateUtils.str2Date(sendDate));
 			}
 		} catch (ParseException e) {
 			e.printStackTrace();
 			return false;
 		}
 	}
 	/**
 	* 重新發送當前已死亡的消息
 	* @return
 	*/
 	@GetMapping("/send/retry")
 	public boolean retrySendDieMessage() {
 		return transactionMessageService.retrySendDieMessage();
 	}
 	/**
 	* 分頁查詢具體狀態的消息
 	* @param query
 	* @param status
 	* @return
 	*/
 	@PostMapping("/query")
 	public List<TransactionMessage> findMessageByPage(@RequestBody MessageQuery query) {
 		return transactionMessageService.findMessageByPage(query,
 				TransactionMessageStatusEnum.parse(query.getStatus()));
 	}
}
