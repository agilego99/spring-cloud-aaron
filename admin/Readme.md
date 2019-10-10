# Admin：監控所有 Spring boot 應用的健康狀況，提供即時警報功能

###### 將 Actuator 中的資訊進行介面化的展示，也可以監控所有 Spring boot 應用的健康狀況，提供即時警報功能

###### 主要功能如下：
- 顯示應用程序的監控狀態
- 應用程序的上下線監控
- 查看 JVM，執行序資訊
- 可視化的查看日誌以及下載日誌文件
- 動態切換日誌級別
- Http 請求資訊跟蹤
- 其他功能...

## 參數
- Spring boot：2.0.6.RELEASE
- Spring cloud：Finchley.SR2
- Java：8
- 專案
    - aaron-spring-boot-admin-client：Admin Client 端
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-spring-boot-admin-client
        - Version：0.0.1-SNAPSHOT
    - aaron-spring-boot-admin：Srping Boot Admin 服務端
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-spring-boot-admin
        - Version：0.0.1-SNAPSHOT
    - aaron-spring-boot-admin-eureka：Srping Boot Admin 服務端；Erueka 客戶端；整合郵件報警、第三方平台報警
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-spring-boot-admin-eureka
        - Version：0.0.1-SNAPSHOT
- 服務依賴
    - aaron-erueka-server
    - aaron-sprig-boot-admin
    - aaron-sprig-boot-admin-client
    - aaron-sprig-boot-admin-eureka

## aaron-spring-boot-admin-client：Srping Boot Admin 客戶端

### 開發
###### 將服務務註冊到服務端
- pom.xml
```
	    <!-- 將服務註冊到 Spring Boot Admin -->
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-client</artifactId>
			<version>2.0.2</version>
		</dependency>
```
- application.properties
```
# Spring Boot Admin 端網址
spring.boot.admin.client.url=http://localhost:9091
```
###### 暴露出資料端點
- pom.xml
```
		<!-- actualtor 依賴 -->
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
- application.properties
```
# 暴露出資料端點
management.endpoints.web.exposure.include=*
```
###### 在 Admin 中查看服務日誌
```
# 供 Admin 中查看服務日誌
logging.file=/Users/Kevin/Agile-MAC/eclipseWorkspace-MAC/Spring-Cloud/logs/Downloads/aaron-spring-boot-admin-client.log
```

### 配置
- application.properties
```
server.port=9092
```
###### 開啟認證
- application.properties
```
# 如果 Spring Boot Admin 服務端如果開啟了認證，監控的客戶端也要配置對應的帳號及密碼
# 帳號及密碼必須與 Spring Boot Admin Server 一致
spring.boot.admin.client.username=aaron
spring.boot.admin.client.password=999999
```

### 測試
###### 將服務務註冊到服務端
```
# 啟動 aaron-spring-boot-admin 及 aaron-spring-boot-admin-client
# 開啟 Browser http://localhost:9091
```
註冊服務資訊
![3d5a975f7b65877b039f8d7151eacb15](imgs/4ECA8E63-3595-4FBD-937A-A6C34B6C6584.png)
###### 暴露出資料端點
```
# 啟動 aaron-spring-boot-admin 及 aaron-spring-boot-admin-client
# 開啟 Browser http://localhost:9091
```
![887aa9ff2749eac02f6eb4fe8be53cb7](imgs/44A7C638-54C0-42CA-AA93-7D61F59A1421.png)
![95d42019e4c0eb9f8bd2fff3e94b67a5](imgs/F51E6125-D467-4140-A261-BCB78475D2CD.png)
###### 在 Admin 中查看服務日誌
```
# 啟動 aaron-spring-boot-admin 及 aaron-spring-boot-admin-client
# 開啟 Browser http://localhost:9091
```
![63ae1b7349fd11728bc1faca32776762](imgs/05BFE5A7-4950-4916-865E-B5CFD24FA087.png)

###### 開啟認證
```
# 啟動 aaron-spring-boot-admin 及 aaron-spring-boot-admin-client
# 開啟 Browser http://localhost:9091
# 帳號 aaron
# 密碼 999999
```
![ee300b61ac8f5b0fd176f094648e67f2](imgs/76FFFBA2-7C37-4019-85FF-BE82701E52E3.png)

### 維運

## aaron-spring-boot-admin：Sring Boot Admin 服務端

### 開發
###### 開啟認證
- pom.xml
```
       <!-- 開啟認證 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
```
- application.properties
```
# 配置認證資料
spring.security.user.name=aaron
spring.security.user.password=999999
```
- App.java
```
    // 自定 Security 配置
    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        private final String adminContextPath;
        public SecurityPermitAllConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            // 靜態資源和登錄頁面可以不用認證
            http.authorizeRequests().antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    // 其他請求必須認證
                    .anyRequest().authenticated()
                    // 自定義登錄和退出
                    .and().formLogin()
                    .loginPage(adminContextPath + "/login").successHandler(successHandler).and().logout()
                    .logoutUrl(adminContextPath + "/logout")
                    // 啓用HTTP-Basic，用於Spring Boot Admin Client註冊
                    .and().httpBasic()
                    .and().csrf().disable();
        }
    }
```

###### 監控服務之二：第三方平台（釘釘機器人發送消息；使用前須先取得 Token）
- DingDingMessageUtil.java 釘釘消息工具類
```
/**
* 應用名稱：釘釘機器人報警
* https://open-doc.dingtalk.com/microapp/serverapi2/qf2nxq
* 請求 Token
* @author aaron
*
*/
public class DingDingMessageUtil {
//    public static String access_token = "填寫你自己申請的token";
    public static String access_token = "b1f556d8bbf032bdb2899b3ead5cdaf2";

    public static void sendTextMessage(String msg) {
        try {
            Message message = new Message();
            message.setMsgtype("text");
            message.setText(new MessageInfo(msg));
            URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);
            // 建立http連接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            conn.connect();
            OutputStream out = conn.getOutputStream();
            String textMessage = JsonUtils.toJson(message);
            byte[] data = textMessage.getBytes();
            out.write(data);
            out.flush();
            out.close();
            System.out.println(conn.getResponseCode());
            InputStream in = conn.getInputStream();
            byte[] data1 = new byte[in.available()];
            in.read(data1);
            System.out.println(new String(data1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        DingDingMessageUtil.sendTextMessage("hello");
    }
}
class Message {
    private String msgtype;
    private MessageInfo text;
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public MessageInfo getText() {
        return text;
    }
    public void setText(MessageInfo text) {
        this.text = text;
    }
}
class MessageInfo {
    private String content;
    public MessageInfo(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

```
- DingDingNotifier.java
```
**
 * 服務監控釘釘通知
 * 通過繼承 AbstractStatusChangeNotifier 實現釘釘發送機制
 * @author aaron
 * @date 2019-7-5
 */
public class DingDingNotifier extends AbstractStatusChangeNotifier {
    public DingDingNotifier(InstanceRepository repository) {
        super(repository);
    }
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
    	String serviceName = instance.getRegistration().getName();
    	String serviceUrl = instance.getRegistration().getServiceUrl();
    	String status = instance.getStatusInfo().getStatus();
    	Map<String, Object> details = instance.getStatusInfo().getDetails();
    	
    	System.err.println("發送至阿里釘釘警報系統");
    	StringBuilder str = new StringBuilder();
    	str.append("【" + serviceName + "】");
    	str.append("【服務地址】" + serviceUrl);
    	str.append("【狀態】" + status);
    	str.append("【詳情】" + JsonUtils.toJson(details));
    	System.err.println(str);
        return Mono.fromRunnable(() -> {
        	DingDingMessageUtil.sendTextMessage(str.toString());
        });
    }
}
```
- App.java
```
...
  // 啟用 DingDingNotifier
  @Bean
    public DingDingNotifier dingDingNotifier(InstanceRepository repository) {
        return new DingDingNotifier(repository);
    }
...

    // 循環通知配置；每10秒發出一筆告警，直到服務正常才會停止
    @Primary
    @Bean(initMethod = "start", destroyMethod = "stop")
    public RemindingNotifier remindingNotifier(InstanceRepository repository) {
        RemindingNotifier notifier = new RemindingNotifier(dingDingNotifier(repository), repository);
        notifier.setReminderPeriod(Duration.ofSeconds(10));
        notifier.setCheckReminderInverval(Duration.ofSeconds(10));
        return notifier;
    }
```

### 配置
- pom.xml
```
	    <!-- Spring Boot Admin 依賴 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- Spring Boot Admin 依賴 -->
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-server</artifactId>
			<version>2.0.2</version>
		</dependency>
```
- application.properties
```
server.port=9091
```
### 測試
###### 監控服務之二：第三方平台（釘釘機器人發送消息；使用前須先取得 Token）
![76719176fd7a46661c6dcb5569b6ab42](imgs/7B5CE0EF-7E92-44D8-A687-837EA7D00449.png)

### 維運


## aaron-spring-boot-admin-eureka：Spring Boot Admin 服務端；Erueka 客戶端；整合郵件報警、第三方平台報警
```
# 按順序啟動以下服務 
# aaron-eureka-server
# aaron-spring-boot-admin-eureka
# aaron-spring-boot-admin-client
# 停止 aaron-spring-boot-admin-client
```
![ae918b26a4d8fd0056b9ff01ac8ec132](imgs/97E3944F-109B-4EA2-B13A-6FA8472B7AFC.png)

### 開發

###### 整合 Eureka
- pom.xml
```
		<!-- Erueka 依賴 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```
- App.java
```
// 增加 Eureka 啟動類
@EnableDiscoveryClient 
@EnableAdminServer
@SpringBootApplication
public class App {
    public static void main(String[] args) {

...
```
- application.properties
```
# 配置認證資料
spring.security.user.name=aaron
spring.security.user.password=999999

# 配置 Eureka 註冊資料
eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8761/eureka
eureka.instance.preferIpAddress=true
eureka.instance.instance-id=${spring.application.name}:${spring.cloud.client.ip-address}:${server.port}

# 暴露出資料端點（Admin Servre 自身監控）
management.endpoints.web.exposure.include=*

# 配置認證資料（需與 Spring Admin Server 一致）
eureka.instance.metadata-map.user.name=aaron
eureka.instance.metadata-map.user.password=999999
```

###### 監控服務之一：郵件報警
- pom.xml
```
		<!-- 郵件報警 -->
		 <dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
```
- 向 Google 申請應用程式授權碼
![11024842954aa28f6bcffb52a0134cae](imgs/79A206D8-1CA5-4FB9-ACC8-BF7E0067DBD6.png)

- application.properties
```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
  
spring.mail.username=郵件帳號
# 本例為透過 Google 兩階段認證後，在新增應用程式授權碼（16位授權碼）
spring.mail.password=xxxx
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.display.sendmail:aaron-spring-boot-admin-eureka
spring.mail.properties.mail.display.sendname:Srping Boot Admin
# 發送給誰 xxx@xxx.com
spring.boot.admin.notify.mail.to=發送給誰
# 是誰發送出去 xxx@xxx.com
spring.boot.admin.notify.mail.from=發送者

spring.mail.default-encoding=utf-8
spring.mail.test-connection=true
```

### 配置
- pom.xml
```
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-server</artifactId>
			<version>2.0.2</version>
		</dependency>
```
- application.properties
```
server.port=9091
spring.application.name=aaron-spring-boot-admin-eureka
```

### 測試
###### 整合 Eureka
```
# 按順序啟動以下服務 
# aaron-eureka-server
# aaron-spring-boot-admin-eureka
# aaron-spring-boot-admin-client
# 登入 Eureka Server http://localhost:8761
# 點擊 Spring Boot Admin Server aaron-spring-boot-eureka
# 帳號 aaron
# 密碼 999999
```
Eureka Server 畫面
![4fd88005666cb9a769719bd38c4ff3e5](imgs/D6E788E1-D0BF-4404-9C92-158608A654F3.png)
Spring Boot Admin Server 登入畫面
![703a69fddb490d8948aa1148b8eeb417](imgs/19293790-7157-431D-A8DA-545B20CB9EDD.png)
監控實例（包含自身）
![f9ccb8ef411394a6c8597a9dc6e14ceb](imgs/74643D7B-2D1E-4D80-8182-50F0EBC83552.png)
aron-spring-boot-admin-eureka 監控畫面
![5844141d687198d0d503b835e03d620b](imgs/D02337DE-F546-47D1-ADFE-939D004389B7.png)
aron-spring-boot-admin-client 監控畫面
![92e701baf63844f48a217c2ec8220f60](imgs/0F7A8590-BD79-4D99-92A2-C8181868258E.png)

###### 監控服務之一：郵件報警
```
# 按順序啟動以下服務 
# aaron-eureka-server
# aaron-spring-boot-admin-eureka
# aaron-spring-boot-admin-client
# 停止 aaron-spring-boot-admin-client
# 開啟郵件軟體
```
Spring Boot Admin Server 報警狀態
![3b865f05912dff130de9d8931579833b](imgs/2ECB9071-C60B-4A78-9D54-D6788AEDA652.png)
報警郵件
![a0d681aaa6fab33df9542139076677b7](imgs/EB396FBF-1B52-45E2-AEA2-8519D2E95106.png)

### 維運

