# Ribbon：基於客戶端的負載均衡組件
- Ribbon 是 Netflix 開源的一款用於客戶端的負載均衡的工具軟體。
![277a7cbc2161e3a27c834b506b8f1a6d](imgs/1329729F-9D78-4937-B72F-BE3CCD7E9B9D.png)
![b60c88387e303c1acc51dc4cc628d5c6](imgs/DFB8C488-E2E6-41BB-BB48-81E6AF9E674F.png)
## 參數
- Spring boot：2.0.6.RELEASE
- Spring cloud：Finchley.SR2
- Java：8
- 專案
       - aaron-ribbon-native-demo：在 native Java 客戶端使用負載均衡
        - Main：RibbonTest
        - Group id：com.aaron
        - Artifact id：aaron-ribbon-native-demo
        - Version：0.0.1-SNAPSHOT
      - aaron-ribbon-eureka-demo：負載均衡服務端、自定義攔截器、Ribbon API 使用
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-ribbon-eureka-demo
        - Version：0.0.1-SNAPSHOT
    - aaron-ribbon-config-demo：Ribbon 飢餓加載
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-ribbon-config-demo
        - Version：0.0.1-SNAPSHOT
    - aaron-ribbon-retry-demo：重試機制
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-ribbon-config-demo
        - Version：0.0.1-SNAPSHOT
- 服務依賴
    - aaron-eureka-server
    - aaron-eureka-client-user-service

## aaron-ribbon-native-demo：在 native Java 客戶端使用負載均衡

### 開發
```
 // 服務列表
        List<Server> serverList = Arrays.asList(new Server("localhost", 8081), new Server("localhost", 8083));
        
// 構建負載實例
        BaseLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
// 採用 RoundRobin 策略        
        loadBalancer.setRule(new RoundRobinRule());
```

### 配置

### 測試
- 啟動 aaron-eureka-client-user-service 專案
```
# 調整 apoplication.properties 並且以下列參數分別起動
server.port=8081
server.port=8083


# 開啟飢餓加載
ribbon.eager-load.enabled=true
# 飢餓加載的服務名
ribbon.eager-load.clients=aaron-ribbon-config-demo

```
![d307957aa34513250eacab0c297ea2ba](imgs/26D6B9B4-AFEE-424A-8293-101BDE3EA8BA.png)

- 執行 RibbonTest
![288cb60edc468eea3cb42e91566ff8ad](imgs/CC51B8C9-AAD1-4B92-A4D1-6AB21C03A062.png)

### 維運

## aaron-ribbon-eureka-demo：負載均衡服務端、自定義攔截器、Ribbon API 使用、飢餓加載

### 開發
#### 負載均衡配置
- @LoadBalanced
```
# 在 RestTemplate 增加攔截器，在請求之前對請求的地址進行替換或者根據具體的負載策略選擇服務地址，然後再去調用，這就是 @LoadBalanced 的原理。
```
#### 實現一個簡單的攔截器
- BeanConfiguration.java
```
# 使用自訂義攔截器 @MyLoadBalanced
import com.aaron.ribbon_eureka_demo.loadbalanced.MyLoadBalanced;
// 註解 @LoadBalanced
@MyLoadBalanced
```

- MyLoadBalancerInterceptor.java
```
# 實現一個簡單的攔截器
	@Bean
	public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer(LoadBalancerClient loadBalancerClient,
			LoadBalancerRequestFactory requestFactory) {
		return new SmartInitializingSingleton() {
			public void afterSingletonsInstantiated() {
				for (RestTemplate restTemplate : MyLoadBalancerAutoConfiguration.this.restTemplates) {
					List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
					list.add(myLoadBalancerInterceptor(loadBalancerClient,requestFactory));
					restTemplate.setInterceptors(list);
				}
			}
		};
	}
```

- MyLoadBalanced.java
```
# 自地義攔截器註解 @MyLoadBalanced
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD }) @Retention(RetentionPolicy.RUNTIME) 
@Documented 
@Inherited 
@Qualifier 
public @interface MyLoadBalanced {}
```

- MyLoadBalancerAutoConfiguration.java
```
# 定義一個配置類，為 RestTemplate 注入攔截器
@MyLoadBalanced
```

#### Ribbon API 使用
- HouseClientController.java
```
# 透過 Ribbon 獲取對應的服務資訊；比如要獲取 aaron-ribbon-eureka-demo 服務資訊，可以使用 oadBalancer.choose("service name"); 來取得相關資訊。
@GetMapping("/choose") 	
	public Object chooseUrl() { 		
		ServiceInstance instance = loadBalancer.choose("aaron-ribbon-eureka-demo");
		return instance; 	
	}
```

#### Ribbon 飢餓加載
- Ribbon 在進行客戶端負載均衡的時候並不是在啓動時就加載上下文，而是在實際請求的時候才去創建，因此這個特性往往會在第一次調用顯得頗為疲軟乏力，嚴重的時候會引起調用超時。所以可以通過指定 Ribbon 具體的客戶端的名稱來開啓飢餓加載，即在啓動的時候便加載所有配置項的應用程序上下文。

### 配置
- application.properties
```
spring.application.name=aaron-ribbon-eureka-demo
server.port=8081

# 開啟飢餓加載
ribbon.eager-load.enabled=true
# 飢餓加載的服務，設定多個時以逗號相隔
ribbon.eager-load.clients=aaron-ribbon-eureka-demo
```
### 測試
- 攔截器作用結果
![4512e85a2c36dbc639c4b5317d446e57](imgs/29933DFD-7A0E-4BF6-8B04-65C66629B0E8.png)

### 維運
- 服務接口：HouseController.java
```
# @RequestParam
GET http://localhost:8081/house/data?name=台北

# @PathVariable Postma不支援，需使用 Browser 調用
GET http://localhost:8081/house/data/台北

# @RequestBody
POST  http://localhost:8081/house/save
RequestBody
{
    "id": 1,
    "city": "新北市",
    "region": "新莊區",
    "name": "頭前公園"
}
```
- 調用接口：HouseClientController.java
```
# Ribobn API
GET http://localhost:8081/choose

# @RequestParam
GET http://localhost:8081/call/data?name=台北

# @PathVariable Postma不支援，需使用 Browser 調用
GET http://localhost:8081/call/data/高雄

# @RequestParam
GET http://localhost:8081/call/dataEntity?name=台北


# @RequestBody
POST  http://localhost:8081/call/save
RequestBody
{
    "id": 1,
    "city": "新北市",
    "region": "新莊區",
    "name": "頭前公園"
}
```


## aaron-ribbon-config-demo：自定負載均衡
##### 負載均衡策略
- BestAvailabl：選擇一個最小的並發請求的Server，逐個考察Server，如果Server被tripped了，則跳過。
- AvailabilityFilteringRule：過濾掉那些一直連接失敗的被標記為circuit tripped的後端Server，並過濾掉那些高並發的的後端Server或者使用一個AvailabilityPredicate來包含過濾server的邏輯，其實就就是檢查status里記錄的各個Server的運行狀態。
- ZoneAvoidanceRule：複合判斷Server所在區域的性能和Server的可用性選擇Server。
- RandomRule：隨機選擇一個Server。
- RoundRobinRule：輪詢選擇， 輪詢index，選擇index對應位置的Server。
- RetryRule：對選定的負載均衡策略機上重試機制，在一個配置時間段內當選擇Server不成功，則一直嘗試使用subRule的方式選擇一個可用的server。
- ResponseTimeWeightedRule：作用同WeightedResponseTimeRule，二者作用是一樣的，ResponseTimeWeightedRule後來改名為WeightedResponseTimeRule。
- WeightedResponseTimeRule：根據響應時間分配一個weight(權重)，響應時間越長，weight越小，被選中的可能性越低。

### 開發
- MyRule.java
```
# 主要選擇服務邏輯在 choose 方法中。這裡缺少選擇邏輯，直接返回服務列表中的第一個服務。
@Override
	public Server choose(Object key) {
		List<Server> servers = lb.getAllServers();
		for (Server server : servers) {
			System.out.println(server.getHostPort());
		}
        # 返回服務列表中的第一個服務
		return servers.get(0);
	}
```

### 配置
- application.properties
```
spring.application.name=aaron-ribbon-config-demo
server.port=8082

#請求連接的超時時間
aaron-ribbon-config-demo.ribbon.ConnectTimeout=1000
#請求處理的超時時間
aaron-ribbon-config-demo.ribbon.ReadTimeout=1000
#對當前實例的重試次數
aaron-ribbon-config-demo.ribbon.maxAutoRetries=1
#切換實例的重試次數
aaron-ribbon-config-demo.ribbon.maxAutoRetriesNextServer=3
#對所有操作請求都進行重試
aaron-ribbon-config-demo.ribbon.okToRetryOnAllOperations=true
aaron-ribbon-config-demo.ribbon.retryableStatusCodes=500,404,502
#aaron-ribbon-config-demo.ribbon.NFLoadBalancerRuleClassName=com.aaron.ribbon_eureka_demo.rule.MyRule
#aaron-ribbon.eureka.enabled=false
#aaron-ribbon-config-demo.ribbon.listOfServers=localhost:8081,localhost:8082
```
### 測試

### 維運
- 調用接口
```
@RequestParam
GET http://localhost:8082/call/data?name=台北
```
- 服務接口
```
@RequestParam
GET http://localhost:8082/house/data?name=台北
```


## aaron-ribbon-retry-demo：重試機制


### 開發

### 配置
- pox.xml
```
# 除了使用 Ribbon 自帶的重試策略，還可以通過集成 Spring Retry 來進行重試操作。只要添加以下依賴：
<dependency>
   <groupId>org.springframework.retry</groupId>    <artifactId>spring-retry</artifactId>
</dependency>
```

- application.properties
```
spring.application.name=aaron-ribbon-retry-demo
server.port=8082

eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8761/eureka/
eureka.instance.preferIpAddress=true
eureka.instance.instance-id=${spring.application.name}:${spring.cloud.client.ip-address}:${server.port}

ribbon.eager-load.enabled=true
ribbon.eager-load.clients=aaron-ribbon-eureka-demo

#ribbon-eureka-demo.ribbon.ConnectTimeout=1000
#ribbon-eureka-demo.ribbon.ReadTimeout=1000
#ribbon-eureka-demo.ribbon.maxAutoRetries=1
#ribbon-eureka-demo.ribbon.maxAutoRetriesNextServer=3
#ribbon-eureka-demo.ribbon.okToRetryOnAllOperations=true
#ribbon-eureka-demo.ribbon.retryableStatusCodes=500,404,502

ribbon.ConnectTimeout=1000
ribbon.ReadTimeout=1000
ribbon.maxAutoRetries=1
ribbon.maxAutoRetriesNextServer=3
ribbon.okToRetryOnAllOperations=true
ribbon.retryableStatusCodes=500,404,502
```
### 測試

### 維運
- 調用接口
```
@RequestParam
GET http://localhost:8082/call/data?name=台北
```




