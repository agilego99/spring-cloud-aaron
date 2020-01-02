# Sprintn Boot@ELK

## 參數
- CentOS 7.5
- Spring boot：2.0.6.RELEASE
- Spring cloud：Finchley.SR2
- Java：8
- 專案
    - aaron-eureka-server-elk：日誌收集
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-eureka-server-elk
        - Version：0.0.1-SNAPSHOT
- 服務依賴
  - ELK(Docker)
        - [安裝 ELK(Docker-compose)](../server/ELK(Docker-compose))
### 開發
- pom.xml 導入相關套件
```
		<dependency>
			<groupId>net.logstash.logback</groupId>
			<artifactId>logstash-logback-encoder</artifactId>
			<version>5.2</version>
		</dependency>

		<dependency>
			<groupId>net.logstash.log4j</groupId>
			<artifactId>jsonevent-layout</artifactId>
			<version>1.7</version>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
		</dependency>
```
- logback.xml logstash 相關配置
```
    ..............    
    <appender name="logstash" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>${logstash.ip_port}</destination>
         <encoder charset="UTF-8"
                 class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp>
                    <timeZone>UTC</timeZone>
                </timestamp>
                <pattern>
                    <pattern>
                        {
                        "appname": "${spring.application.name}",
                        "level": "%level",
                        "class": "%logger{40}",
                        "message": "%message"
                        }
                    </pattern>
                </pattern>
            </providers>
        </encoder>
        <queueSize>1048576</queueSize>
        <keepAliveDuration>5 minutes</keepAliveDuration>
    </appender>

    <logger name="logstash_name1" level="INFO" additivity="false">
        <appender-ref ref="logstash"/>
    </logger>
    <logger name="logstash_name2" level="INFO" additivity="false">
        <appender-ref ref="logstash"/>
    </logger>
    ..............
```
- EurekaStateChangeListener 使用 elk 日誌物件
```
@Component
@Slf4j
public class EurekaStateChangeListener {
	
	Logger elkLogger1 = LoggerFactory.getLogger(LogStashUtil.LOGSTASH_NAME1);
	Logger elkLogger2 = LoggerFactory.getLogger(LogStashUtil.LOGSTASH_NAME2);
	
	 @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.err.println("註冊中心 啓動");
        // 使用方式
        logstashName1.info(LogStashUtil.concat("elk 測試","進行註冊"));
        logstashName2.info(LogStashUtil.concat("elk 測試","進行註冊"));

    }
    ..............
}
```
- LogUtil.java 定義欲收集的日誌種類
```
public class LogStashUtil {
	
	public static final String LOGSTASH_NAME1 = "logstash_name1";
	public static final String LOGSTASH_NAME2 = "logstash_name2";
	.......
    

}
```

### 測試
![b7ba668619fd2bcfdec241dadc515f97](imgs/572B3796-D1DF-4C47-8B75-B86453C9642C.png)

### 維運

  