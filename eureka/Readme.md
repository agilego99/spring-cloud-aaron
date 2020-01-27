# Eureka：服務監控
![9d262e926ba0037d21acebc2128f8c8c](imgs/22E473A7-DC22-4920-850C-15468BF06AF5.png)

##### CAP 定理
- 分布式系統領域有個著名的 CAP 定理（帽子定理）：這三個特性在任何分佈至系統中都不能同時滿足，最多同時滿足兩個。
    - C 數據一致性
    - A 服務可用性
    - P 服務對網路分區故障的容錯性。
    - ZooKeeper 是基於 CP 原則構建（保證數據的一致性）
        - Leader 負責寫入數據
    - Eureka 是基於 AP 構建（保證服務的可用性)

### 參數
- Spring boot：2.0.6.RELEASE
- Spring cloud：Finchley.SR2
- Java：8
- 專案
    - aaron-eureka-server：註冊中心服務
        - Main：EurekaServerApplication
        - Group id：com.aaron
        - Artifact id：aaron-eureka-server
        - Version：0.0.1-SNAPSHOT
    - aaron-eureka-client-user-service：服務提供者
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-eureka-client-user-service
        - Version：0.0.1-SNAPSHOT
    - aaron-eureka-client-article-service：服務調用者
        - Main：App
        - Group id：com.aaron
        - Artifact id：eureka-client-article-service
        - Version：0.0.1-SNAPSHOT
    - aaron-eureka-server-cluster：高可用集群搭建
        - Main：App
        - Group id：com.aaron
        - Artifact id：eureka-server-cluster
        - Version：0.0.1-SNAPSHOT


## aaron-eureka-server：註冊中心服務

### 開發

### 配置
- application.properties
```
server.port=8761
# 關閉自我保護：eureka.server.enable-self-preservation=true
# 如果在 Web 控制台看到以下圖示的內容，那就證明進入保護模式了。
```
![a00d84cc1f86d16ba9dd16a610e548c9](imgs/DF38BDB3-861A-41D9-AC2C-B5AF2107B8C4.png) 

### 測試

### 維運
- Eurka Web 控制台
```
http://localhost:8761
帳號：aaron
密碼：999999
```

## aaron-eureka-client-user-service：服務提供者

### 開發

### 配置
- application.properties
```
spring.application.name=aaron-eureka-client-user-service
server.port=8083
```

### 測試
![241cc5202fb4099d477bcae5031ab3b0](imgs/E1864154-C470-4AC4-BBC6-E8F72A834B21.png)

### 維運
- 服務接口
`GET http://localhost:8083/user/hello` 


## aaron-eureka-client-article-service：服務調用者
![1e7224e0200286e9ad08b8ef373bd920](imgs/5D884569-A99E-4CAD-9BC1-204CF03E8C32.png)


### 開發

### 配置
- application.properties
```
spring.application.name=aaron-eureka-client-article-service
server.port=8082

# 自定義 metadata
# Eureka 的元數據有兩種，分別是框架定好了的標準元數據和用戶自訂義元數據。
# 標準元數據指的是主機名、IP 地址、端口號、狀態頁和健康檢查等信息。
eureka.instance.metadata-map.gordium=aaron
```

### 測試
![ce5368606bb5502a356bbe88bedded8e](imgs/3D570842-B90A-4790-A84E-264D85221476.png)

![21bcd9affadcdf3e0759719115942892](imgs/413E858F-E61A-499D-A080-2FBD8B43A3FB.png)



### 維運
#### 調用 aaron-eureka-client-user-service 服務的接口
- 服務接口
`GET http://localhost:8082/article/infos`

##### 若要使用以下的 API 則需到 BeanConfiguration 將 @LoadBalanced 進行設置；以將 LoadBalancerClient 接口的實現類註冊到 Spring 容器中
`GET http://localhost:8082/article/callHello2`

##### 若要使用以下的 API 則需到 BeanConfiguration 將 @LoadBalanced 註解；取消 LoadBalancerClient 接口的實現類註冊到 Spring 容器中
`GET http://localhost:8082/article/callHello`

```
@Configuration
public class BeanConfiguration {

	@Bean
	//@LoadBalanced 註解
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
```

## aaron-eureka-server-cluster：Eureka 高可用集群搭建
* Eureka 高可用搭建

### 開發

### 配置
- application.properties
```
spring.application.name=aaron-eureka-server-cluster
spring.profiles.active=slaveone
```

#### 以 two nodes 為例來說明搭建方式：
```
server.port=8761
# 將 slaveone 註冊到此（master）
eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8762/eureka/
```
- application-slaveone.properties
```
server.port=8762
# 將 master 註冊到此（slaveone）
eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8761/eureka/
```

#### 客戶端使用集群方式連線方式：
`eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8761/eureka/,http://aaron:999999@localhost:8762/eureka/`

### 測試
`master http://localhost:8761`
![78a8e145f343d582d7325fe8cc44e135](imgs/84DEEEB2-8E28-4F3C-9600-9362930858B6.png)
`slaveone http://localhost:8762`
![64dbd6d8f68eb42e8782f7d8ea362be0](imgs/8A7E0EA5-B01A-4056-813B-1A2EC82D5FDC.png)

### 維運
- 啟動
    - 在 A 機器 `--spring.profiles.active=master`
    - 在 B 機器 `--spring.profiles.active=slaveone`