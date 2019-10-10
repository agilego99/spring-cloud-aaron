package com.aaron.mqclient.dto;
import java.util.Date;
public class TransactionMessage {
 	// 消息ID
 	private Long id;
 	
 	// 消息內容，JSON格式數據
 	private String message;
 	
 	// 發送給哪個隊列
 	private String queue;
 	
 	// 哪個系統發送出去的
 	private String sendSystem;
 	
 	// 發送次數，每重新發送一次+1
 	private int sendCount;
 	
 	// 消息創建時間
 	private Date createDate;
 	
 	// 最近發送消息時間，每發送一次更新時間
 	private Date sendDate;
 	
 	// 狀態：0等待消費  1已消費  2已死亡
 	private int status = 0;
 	
 	// 死亡次數條件，由使用方決定，默認為發送10次還沒被消費則標記死亡,人工介入
 	private int dieCount = 10;
 	
 	// 消息被消費的時間
 	private Date customerDate;
 	
 	// 哪個系統消費了該消息
 	private String customerSystem;
 	// 消息死亡時間
 	private Date dieDate;
 	
 	public Long getId() {
 		return id;
 	}
 	public void setId(Long id) {
 		this.id = id;
 	}
 	public String getMessage() {
 		return message;
 	}
 	public void setMessage(String message) {
 		this.message = message;
 	}
 	public String getQueue() {
 		return queue;
 	}
 	public void setQueue(String queue) {
 		this.queue = queue;
 	}
 	public String getSendSystem() {
 		return sendSystem;
 	}
 	public void setSendSystem(String sendSystem) {
 		this.sendSystem = sendSystem;
 	}
 	public int getSendCount() {
 		return sendCount;
 	}
 	public void setSendCount(int sendCount) {
 		this.sendCount = sendCount;
 	}
 	public Date getCreateDate() {
 		return createDate;
 	}
 	public void setCreateDate(Date createDate) {
 		this.createDate = createDate;
 	}
 	public Date getSendDate() {
 		return sendDate;
 	}
 	public void setSendDate(Date sendDate) {
 		this.sendDate = sendDate;
 	}
 	public int getStatus() {
 		return status;
 	}
 	public void setStatus(int status) {
 		this.status = status;
 	}
 	public int getDieCount() {
 		return dieCount;
 	}
 	public void setDieCount(int dieCount) {
 		this.dieCount = dieCount;
 	}
 	public Date getCustomerDate() {
 		return customerDate;
 	}
 	public void setCustomerDate(Date customerDate) {
 		this.customerDate = customerDate;
 	}
 	public String getCustomerSystem() {
 		return customerSystem;
 	}
 	public void setCustomerSystem(String customerSystem) {
 		this.customerSystem = customerSystem;
 	}
 	
 	public Date getDieDate() {
 		return dieDate;
 	}
 	public void setDieDate(Date dieDate) {
 		this.dieDate = dieDate;
 	}
}
