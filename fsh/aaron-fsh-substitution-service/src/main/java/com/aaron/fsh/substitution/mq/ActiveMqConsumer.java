package com.aaron.fsh.substitution.mq;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.aaron.fsh.substitution.dto.MessageDto;
import com.aaron.fsh.substitution.dto.UpdateHouseNameDto;
import org.gordianknot.common.util.JsonUtils;
import com.aaron.mqclient.TransactionMqRemoteClient;
/**
 * 可靠消息服務消費類
 * @author aaron
 *
 */
@Component
public class ActiveMqConsumer {
 	
 	@Autowired
 	private TransactionMqRemoteClient transactionMqRemoteClient;
 	
 	// 小區名稱修改操作
 	@JmsListener(destination = "house_queue")
 	public void receiveQueue(TextMessage text) {
 		try {
 			System.out.println("可靠消息服務消費消息："+text.getText());
 			MessageDto dto = JsonUtils.toBean(MessageDto.class, text.getText());
 			UpdateHouseNameDto houseInfo = JsonUtils.toBean(UpdateHouseNameDto.class, dto.getMessage());
 			// 執行修改操作 ....
 			//System.out.println(2/0);
 			// service.update(houseInfo)
 			//修改成功後調用消息確認消費接口，確認該消息已被消費
 			boolean result = transactionMqRemoteClient.confirmCustomerMessage("substitution-service", dto.getMessageId());
 			//手動確認ACK
 			if (result) {
 				text.acknowledge();
 			}
 		} catch (Exception e) {
 			// 異常時會觸發重試機制，重試次數完成之後還是錯誤，消息會進入DLQ隊列中
 			throw new RuntimeException(e);
 		}
 		
 	}
}
