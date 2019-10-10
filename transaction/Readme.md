# Transaction
##### Two-phase Commit
二階段提交（英語：Two-phase Commit）是指在計算機網絡以及資料庫領域內，為了使基於分布式系統架構下的所有節點在進行事務提交時保持一致性而設計的一種演算法。通常，二階段提交也被稱為是一種協議（Protocol）。在分布式系統中，每個節點雖然可以知曉自己的操作時成功或者失敗，卻無法知道其他節點的操作的成功或失敗。當一個事務跨越多個節點時，為了保持事務的ACID特性，需要引入一個作為協調者的組件來統一掌控所有節點（稱作參與者）的操作結果並最終指示這些節點是否要把操作結果進行真正的提交（比如將更新後的數據寫入磁碟等等）。因此，二階段提交的算法思路可以概括為： 參與者將操作成敗通知協調者，再由協調者根據所有參與者的反饋情報決定各參與者是否要提交操作還是中止操作。
##### TCC 補償型
TCC 又稱補償事務。其核心概念是：針對每個操作都要註冊一個與其對應的確認和補償（撤銷操作）"。它分為三個操作：
###### Try階段：主要是對業務系統做檢測及資源預留。
- Try：Try 操作是先把多個應用中的業務資源預留和鎖定住，為後續的確認打下基礎，類似的，DML操作要鎖定數據庫記錄行，持有數據庫資源。
###### Confirm階段：確認執行業務操作。
Confirm：Confirm 操作是在 Try 操作中涉及的所有應用均成功之後進行確認，使用預留的業務資源，和 Commit 類似
###### Cancel階段：取消執行業務操作。
- Cancel：Cancel 則是當 Try 操作中涉及的所有應用沒有全部成功，需要將已成功的應用進行取消(即 Rollback 回滾)。其中 Confirm 和 Cancel 操作是一對反向業務操作。
![cf73e47e36f625b2b906e2f9c10a69cb](imgs/085ED32B-F8EA-445F-886F-90B271637316.png)

##### 最終一致性
###### 最終一致性是比較常用的一種方式，與 TCC 相比，成本也較低，通過結合 MQ 來實現非同步處理。由於 MQ 不支持交易，因此須以撰寫程式以補之不足之處。
![5b066f1d60941512beff7d4b0c644828](imgs/054B22FF-EC39-4C2D-93DB-7F873B92B3F6.png)
###### 消息可靠管理系統功能（未來發展）：可以知道每天消息發送量，每個系統的消費量，哪個系統的死亡消息比較多，當前還有多少系統沒有被消費等等...。
- 查詢消息列表
- 根據不同狀態查詢消息
- 對死亡的消息進行重發操作
- 刪除已被消費的消息
##### 最大努力通知行交易
###### 適用於跟外部系統之間的通信，通過定期通知的方式來達到資料的一致性。
##### 支付回調也採用類似原理，支付接口都有一個回調地址，在支付成功後，支付公司會將支付結果返回回調地址，如果沒有收到支付成功的通知，支付公司會重複回調該接口，直到 n 次後才不會再通知。

## 參數
- Spring boot：2.0.6.RELEASE
- Spring cloud：Finchley.SR2
- Java：8
- 專案
    - aaron-transaction-mq-service：可靠消息服務端
        - Main：TransactionMqServiceApplication
        - Group id：com.aaron
        - Artifact id：aaron-transaction-mq-service
        - Version：1.0
    - aaron-transaction-mq-client：可靠消息（Feign）客戶端
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-mongodb
        - Version：0.0.1-SNAPSHOT
    - aaron-transaction-mq-task：負責發送消息
        - Main：TransactionTaskApplication
        - Group id：com.aaron
        - Artifact id：aaron-transaction-mq-task
        - Version：1.0
- 服務依賴
    - Zookeeper
        - [安裝 Zookeeper](../server/Zookeeper)
    - Radis
        - [安裝 Redis](../server/Redis)
    - MySQL
        - [安裝 MySQL](../server/MySQL)
    - ActiceMQ
        - [安裝 ActiveMQ](../server/ActiveMQ)
    - aaron-eureka-server     
    - 要完成實現「最終一致性」交易保證；以下微服務尚需改寫成 Spring boot 2.0.6.RELEASE
        - aaron-fsh-house-service
        - aaron-fsh-substitution-service
        - aaron-fsh-user-service
###### 各微服務間依存關係如下：
![45705bbf22dc0679b2da95f04c841417](imgs/F5B08072-2A6F-42D1-A0BF-993C420FFAF4.png)

## aaron-transaction-mq-service：可靠消息服務端


### 開發

##### 消息服務 Maven 依賴
- pom.xml 消息服務 Maven 依賴
```
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		
	   <!-- JDBC ORM -->		
       <dependency>
			<groupId>org.gordianknot</groupId>
			<artifactId>gordianknot-jdbc</artifactId>
			<version>1.0.3</version>
		</dependency>
		
		<dependency>
		   <groupId>com.alibaba</groupId>
		   <artifactId>druid-spring-boot-starter</artifactId>
		   <version>1.1.10</version>
		</dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
	</dependencies>
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Finchley.SR2</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```
- application.properties 配置
```
eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8761/eureka/
eureka.instance.preferIpAddress=true
eureka.instance.instance-id=${spring.application.name}:${spring.cloud.client.ipAddress}:${spring.application.instance_id:${server.port}}
spring.datasource.druid.url=jdbc:mysql://gordianknot:3306/ds_0?useSSL=false
spring.datasource.druid.username=root
spring.datasource.druid.password=999999
spring.datasource.druid.driver-class-name=com.mysql.jdbc.Driver
# 初始化大小，最小，最大
spring.datasource.druid.initialSize=10
spring.datasource.druid.minIdle=10
spring.datasource.druid.maxActive=50
# 配置獲取連接等待超時的時間
spring.datasource.druid.maxWait=60000
# 配置間隔多久才進行一次檢測，檢測需要關閉的空閒連接，單位是毫秒
spring.datasource.druid.timeBetweenEvictionRunsMillis=60000
# 配置一個連接在池中最小生存的時間，單位是毫秒
spring.datasource.druid.minEvictableIdleTimeMillis=300000
spring.datasource.druid.validationQuery=SELECT 1 FROM DUAL
spring.datasource.druid.testWhileIdle=true
spring.datasource.druid.testOnBorrow=false
spring.datasource.druid.testOnReturn=false
# 定期把監控數據輸出到日誌中
spring.datasource.druid.timeBetweenLogStatsMillis=300000
# 打開PSCache，並且指定每個連接上PSCache的大小
spring.datasource.druid.poolPreparedStatements=true
spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize=20
spring.datasource.druid.filters=stat,wall
# 通過connectProperties屬性來打開mergeSql功能；慢SQL記錄
spring.datasource.druid.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=1000
```
##### 消息存儲表格設計
- TransactionMessage.java 消息實體類
```
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
```

##### 提供服務接口
###### 服務接口對外提供服務，包含所有對消息的操作
- TransactionMessageService.java 所有對消息的操作
```
**
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
```


### 配置
- application.properties
```
spring.application.name=aaron-transaction-mq-service
server.port=3101
```

### 測試
##### 消息存儲表格設計
###### 建立資料表（此前須先建立 ds_0 資料庫）
- table.sql 建立 transaction_message 資料表
```
/*
 Navicat Premium Data Transfer

 Source Server         : gordianknot
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 192.168.56.101:3306
 Source Schema         : ds_0

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 05/09/2019 11:11:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction_message
-- ----------------------------
DROP TABLE IF EXISTS `transaction_message`;
CREATE TABLE `transaction_message` (
  `id` bigint(64) NOT NULL,
  `message` varchar(1000) NOT NULL COMMENT '消息內容',
  `queue` varchar(50) NOT NULL COMMENT '隊列名稱',
  `send_system` varchar(20) NOT NULL COMMENT '發送消息的系統',
  `send_count` int(4) NOT NULL DEFAULT '0' COMMENT '重復發送消息次數',
  `c_date` datetime NOT NULL COMMENT '創建時間',
  `send_date` datetime DEFAULT NULL COMMENT '最近發送消息時間',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '狀態：0等待消費  1已消費  2已死亡',
  `die_count` int(4) NOT NULL DEFAULT '0' COMMENT '死亡次數條件，由使用方決定，默認為發送10次還沒被消費則標記死亡,人工介入',
  `customer_date` datetime DEFAULT NULL COMMENT '消費時間',
  `customer_system` varchar(50) DEFAULT NULL COMMENT '消費系統',
  `die_date` datetime DEFAULT NULL COMMENT '死亡時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
```
![25f85588261d64be8c5ae08151b456be](imgs/2956AF05-75D5-44A2-89F6-9FBA6137F962.png)


### 維運

## aaron-transaction-mq-task：負責發送消息


### 開發
##### 消息發送系統 Maven 依賴
- pom.java 
```
		<!-- 可靠消息（Feign）客戶端 Maven 依賴 -->
        <dependency>
			<groupId>com.aaron</groupId>
			<artifactId>aaron-transaction-mq-client</artifactId>
			<version>1.0</version>
		</dependency>
		<!-- ActiveMQ Maven 依賴 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-activemq</artifactId>
		</dependency>
		<!-- Redis Maven 依賴 -->
		<dependency>
		    <groupId>org.redisson</groupId>
		    <artifactId>redisson-spring-boot-starter</artifactId>
		    <version>3.10.2</version>
		</dependency>
```
- application.properties
```
spring.activemq.broker-url=tcp://gordianknot:61616
spring.activemq.user=
spring.activemq.password=
spring.activemq.in-memory=true
spring.activemq.pooled=false
#配置消息模型為發佈訂閱模式(一個項目中支持一種模式)
#spring.jms.pub-sub-domain=true
spring.redis.host=gordianknot
spring.redis.port=6379
```
- TransactionTaskApplication.java 消息發送啟動類
```
@EnableDiscoveryClient
// 開啟 Feign 
@EnableFeignClients(basePackages = "com.aaron.mqclient")
@SpringBootApplication
public class TransactionTaskApplication {
 	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTaskApplication.class);
 	
 	public static void main(String[] args) {
 		SpringApplication application = new SpringApplication(TransactionTaskApplication.class);
 		ConfigurableApplicationContext content = application.run(args);
        try {
        	// 負責發送消息的處理類
        	ProcessMessageTask task = content.getBean(ProcessMessageTask.class);
        	task.start();
        	new CountDownLatch(1).await();
 		} catch (InterruptedException e) {
 			LOGGER.error("專案啓動異常", e);
 		}
 	}
}
```
- ProcessMessageTask.java 啟動發送消息任務
```
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
}
```
- ProcessMessageTask.java 發送消息
```
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
```
- ProcessMessageTask.java 具體進行消費發送的邏輯
```
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
```

### 配置
- application.properties
```
spring.application.name=aaron-transaction-mq-task
server.port=3105
```

### 測試


### 維運


## aaron-transaction-mq-client：可靠消息（Feign）客戶端


### 開發


### 配置



### 測試


### 維運



