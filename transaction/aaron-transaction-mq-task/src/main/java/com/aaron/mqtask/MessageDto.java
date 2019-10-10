package com.aaron.mqtask;

/**
 * 消息資料傳輸
 * @author aaron
 *
 */
public class MessageDto {
	/**
	 * 消息ID
	 */
	private Long messageId;
	
	/**
	 * 消息内容
	 */
	private String message;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
