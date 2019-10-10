# Swagger：基於 API 文件框架；供前後端開發人員溝通管道
###### 解決 API管理的痛點
- 隨著 API 經濟發展越發壯大，API 安全問題可以說已經進入穩定控制狀態，如何管理好 API 成了凌駕於 API 安全的一項重要工程。API 接口管理目前遇到的最大痛點就是開發時編寫的大量文件在開發過程中需要經常改動，因此文件維護工作量由此變大。
###### 解決接口調用方法和服務方法接口依賴的問題
- 目前最行之有效的解決辦法是將 API 接口管理系統化或平台化，也就是可以在可見的介面上直接管理和維護接口，同時這些可見介面的接口可以直接生成相應的代碼，這樣就能節省大量的開發工作量。當然，更優化的辦法是每當代碼有變動時，介面上的接口都可以進行同步更變，這樣就解決了接口調用方和服務方對於接口依賴的問題。


## 參數
- Spring boot：2.0.6.RELEASE
- Spring cloud：Finchley.SR2
- Java：8
- 專案
    - aaron-swagger-demo
        - Main：AuthApplication
        - Group id：com.aaron
        - Artifact id：aaron-swagger-demo
        - Version：1.0
    - aaron-swagger-demo2
        - Main：AuthApplication
        - Group id：com.aaron
        - Artifact id：id：aaron-swagger-demo2
        - Version：1.0
    - aaron-swagger-zuul
        - Main：App
        - Group id：com.aaron
        - Artifact id：aaron-swagger-zuul
        - Version：0.0.1-SNAPSHOT
- 服務依賴
    - aaron-eureka-server


## aaron-swagger-demo：Swagger API Demo

### 開發
###### 專案集成 Swagger 管理 API 文檔；Swagger Starter Maven 依賴
- pom.xml
```
<!-- 專案中集成 Swagger；Swagger Starter Maven 依賴 -->
		<dependency>
			<groupId>com.spring4all</groupId>
			<artifactId>swagger-spring-boot-starter</artifactId>
			<version>1.7.1.RELEASE</version>
		</dependency>
```
- AuthApplication.java
```
/**
 * 啟用 Swagger
 * @author Aaron
 */
@EnableDiscoveryClient
@EnableSwagger2Doc
@SpringBootApplication
public class AuthApplication {
	public static void main(String[] args) {
	    SpringApplication.run(AuthApplication.class, args);
	}
}
```
###### 使用 Swagger 生成文檔
- UserController.java
```
// 透過 Swagger 生成對應的 API
...
@ApiOperation(value = "新增用戶")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserDto.class) })
    @PostMapping("/user")
    public UserDto addUser(@RequestBody AddUserParam param) {
        System.err.println(param.getName());
        return new UserDto();
    }
...
```
- AddUserParam
```
// API Data Model：新增用戶
@Data
@ApiModel(value = "com.cxytiandi.auth.param.AddUserParam", description = "新增用户参数") 
public class AddUserParam {
	
	@ApiModelProperty(value="ID")
	private String id;
	
	@ApiModelProperty(value="名稱")
	private String name;
	
	@ApiModelProperty(value="年龄")
	private int age;
}
```

###### 整合 Eureka 控制台以快速查看 Swagger 文件
- application.properties
```
# Eureka 控制台快速查看 Swagger 文檔 
eureka.instance.status-page-url=http://${spring.cloud.client.ip-address}:${server.port}/swagger-ui.html
```

###### 請求認證
```
# 其他配置方式請參考 https://github.com/SpringForAll/spring-boot-starter-swagger
swagger.basePackage=com.aaron.auth
# 默認請求的 Head name 為 Token，在此修改為 Authorization
swagger.authorization.key-name=Authorization
```

###### 其它配置（中文無法正常顯示）
```
# Swagger 其它配置
swagger.title=aaron-swagger-demo
swagger.description=This is API document by Swagger created
swagger.termsOfServiceUrl= http://example.com/terms/
swagger.contact.name=Aaron
swagger.contact.url=https://agilego99.blogspot.com
swagger.contact.email=agilego99@gmail.com
```

### 配置
- application.properties
```
spring.application.name=aaron-swagger-demo
server.port=8081
```

### 測試
###### 使用 Swagger 生成文檔 `http://localhost:8081/swagger-ui.html`
Swagger 線上文件
![c8ea74373e6f3f7590f891c945027fc5](imgs/0B8D5918-01B8-45D4-BD26-52FD7C073E0D.png)
![d1896e8b69b9cbac8cb5489cc2aaca47](imgs/CF76C76E-3BAB-4445-916C-DB6C804BA0F8.png)

###### 整合 Eureka 控制台以快速查看 Swagger 文件 `http://localhost:8761`
透過 Eureka 查詢 aaron-swagger-demo Swagger 文件
![0adec840d65694603b0aea348bc05242](imgs/CA11BE4C-58A6-46C0-8651-A79FE9514817.png)
查詢結果
![152cdec818d144ab44778bc6417cdb9c](imgs/4D46EE9A-D010-4571-9F98-EA97146480FB.png)

###### 請求認證
![f6ce83ebff5c91388c3d4e8e00487b21](imgs/A2D87485-9DE7-4BDF-AC9D-54E04E095480.png)

###### 其它配置（中文無法正常顯示）
![2ac575da0ed579cba55ae6b15da8abeb](imgs/FE9DABF3-6D04-4BF7-A970-0BBBE665993C.png)


### 維運

## aaron-swagger-demo2：Swagger API Demo2

### 開發

### 配置
- pom.xml
```
<!-- 專案中集成 Swagger -->
		<dependency>
			<groupId>com.spring4all</groupId>
			<artifactId>swagger-spring-boot-starter</artifactId>
			<version>1.7.1.RELEASE</version>
		</dependency>
```
- application.properties
```
spring.application.name=aaron-swagger-demo2
server.port=8082

eureka.client.serviceUrl.defaultZone=http://aaron:999999@localhost:8761/eureka/
eureka.instance.preferIpAddress=true
eureka.instance.instance-id=${spring.application.name}:${spring.cloud.client.ip-address}:${server.port}

# Eureka 控制台快速查看 Swagger 文檔 
eureka.instance.status-page-url=http://${spring.cloud.client.ip-address}:${server.port}/swagger-ui.html

# 其他配置方式請參考 https://github.com/SpringForAll/spring-boot-starter-swagger
swagger.basePackage=com.aaron.auth
# 默認請求的 Head name 為 Token，在此修改為 Authorization
swagger.authorization.key-name=Authorization

# Swagger 其它配置
swagger.title=aaron-swagger-demo2
swagger.description=This is API document by Swagger created
swagger.termsOfServiceUrl= http://example.com/terms/
swagger.contact.name=Aaron
swagger.contact.url=https://agilego99.blogspot.com
swagger.contact.email=agilego99@gmail.com
```

### 測試

### 維運

## aaron-swagger-zuul：Zuul 聚合多個服務 Swagger

### 開發
###### 聚合多個 Swagger
- application.propertes
```
# Eureka 控制台快速查看 Swagger 文檔 
eureka.instance.status-page-url=http://${spring.cloud.client.ip-address}:${server.port}/swagger-ui.html
```
- pom.xml
```
		<!-- Swagger Maven 依賴 -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
		<!-- Swagger Maven 依賴 -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>
```
- DocumentationConfig.java
```
// Swagger 聚合配置類
@EnableSwagger2
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Override
    public List<SwaggerResource> get() {
    	List<SwaggerResource> resources = new ArrayList<>();
    	// 排除自身，將其他的服務添加進去
    	discoveryClient.getServices().stream().filter(s -> !s.equals(applicationName)).forEach(name -> {
    		resources.add(swaggerResource(name, "/" + name + "/v2/api-docs", "2.0"));
    	});
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
```

### 配置
- application.properties
```
spring.application.name=aaron-swagger-zuul
server.port=2103
```
### 測試
###### 聚合多個 Swagger
透過 Eureka 連結至 Swagger
![2863ea93cb50e02847f812e77f28ccf6](imgs/14D82600-8B3D-42FF-A9F4-3B88A8D939F8.png)
切換 Swagger API 系統
![957fa6e9e4b98f439857e5a31f48e10a](imgs/208AA40C-BE56-45B2-AC50-0958FC8836B2.png)

### 維運