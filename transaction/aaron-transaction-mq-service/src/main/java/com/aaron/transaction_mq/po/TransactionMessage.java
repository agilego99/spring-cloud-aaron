package com.aaron.transaction_mq.po;
import java.util.Date;
import org.gordianknot.jdbc.Orders;
import org.gordianknot.jdbc.Orders.OrderyType;
import org.gordianknot.jdbc.annotation.AutoId;
import org.gordianknot.jdbc.annotation.Field;
import org.gordianknot.jdbc.annotation.TableName;
import com.aaron.transaction_mq.enums.TransactionMessageStatusEnum;
@TableName(value="transaction_message", desc="事務消息表", author="aaron")
public class TransactionMessage {
 	@AutoId
 	@Field(value = "id", desc = "消息ID")
 	private Long id;
 	
 	@Field(value = "message", desc = "消息內容")
 	private String message;
 	
 	@Field(value = "queue", desc = "隊列名稱")
 	private String queue;
 	
 	@Field(value = "send_system", desc = "發送消息的系統")
 	private String sendSystem;
 	
 	@Field(value = "send_count", desc = "重復發送消息次數")
 	private int sendCount;
 	
 	@Field(value = "c_date", desc = "創建時間")
 	private Date createDate;
 	
 	@Field(value = "send_date", desc = "最近發送消息時間")
 	private Date sendDate;
 	
 	@Field(value = "status", desc = "狀態：0等待消費  1已消費  2已死亡")
 	private int status = TransactionMessageStatusEnum.WATING.getStatus();
 	
 	@Field(value = "die_count", desc = "死亡次數條件，由使用方決定，默認為發送10次還沒被消費則標記死亡,人工介入")
 	private int dieCount = 10;
 	
 	@Field(value = "customer_date", desc = "消費時間")
 	private Date customerDate;
 	
 	@Field(value = "customer_system", desc = "消費系統")
 	private String customerSystem;
 	@Field(value = "die_date", desc = "死亡時間")
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
 	public static String[] UPDATE_FIELDS = new String[] {"status", "customer_date", "customer_system"};
 	
 	public static String[] UPDATE_FIELDS2 = new String[] {"status", "die_date"};
 	
 	public static Orders[] ID_ORDERS = new Orders[] { new Orders("id", OrderyType.ASC) };
 	
}
