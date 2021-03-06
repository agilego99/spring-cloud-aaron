package com.aaron.fsh.substitution.dto;
/**
 * 消息數據傳輸
 * @author aaron
 */
public class MessageDto {
 	/**
 	* 消息ID
 	*/
 	private Long messageId;
 	
 	/**
 	* 消息內容
 	*/
 	private String message;
 	public MessageDto() {
 		super();
 	}
 	public MessageDto(Long messageId, String message) {
 		super();
 		this.messageId = messageId;
 		this.message = message;
 	}
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
