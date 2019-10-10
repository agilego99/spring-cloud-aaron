package com.aaron.mqclient;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.aaron.mqclient.dto.TransactionMessage;
import com.aaron.mqclient.query.MessageQuery;

/**
 * 事務消息服務調用客戶端
 * @author aaron
 */
@Component
public class TransactionMqRemoteClientHystrix implements TransactionMqRemoteClient {

	@Override
	public boolean sendMessage(TransactionMessage message) {
		return false;
	}

	@Override
	public boolean sendMessage(List<TransactionMessage> messages) {
		return false;
	}

	@Override
	public boolean confirmCustomerMessage(String customerSystem, Long messageId) {
		return false;
	}

	@Override
	public List<TransactionMessage> findByWatingMessage(int limit) {
		return new ArrayList<TransactionMessage>();
	}

	@Override
	public boolean confirmDieMessage(Long messageId) {
		return false;
	}

	@Override
	public boolean incrSendCount(Long messageId, String sendDate) {
		return false;
	}

	@Override
	public boolean retrySendDieMessage() {
		return false;
	}

	@Override
	public List<TransactionMessage> findMessageByPage(MessageQuery query) {
		return new ArrayList<TransactionMessage>();
	}
	
}
