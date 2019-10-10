# Gordianknot-swagger

##### 框架優勢
- 快速集成 Spring boot
- 直接通過配置定義 API 分組資訊
##### 配置使用
- pom.xml 
```
<!-- Gordianknot-swagger Maven 依賴 -->
<dependency>
	<groupId>org.gordianknot</groupId>
	<artifactId>gordianknot-swagger</artifactId>
	<version>1.0</version>
</dependency>
```
- application.properties 支持多個配置，採用下標方式，從0開始
```
swagger.ui.confs[0].group=loudong
swagger.ui.confs[0].paths=/rest/ld.*
swagger.ui.confs[0].contact=yinjihuan[QQ:1304489315]
swagger.ui.confs[0].version=1.0
swagger.ui.confs[1].group=user_auth
swagger.ui.confs[1].paths=/rest/auth.*
```

## 參數
- Spring boot：1.5.4.RELEASE
- Spring cloud：無
- Java：8
- 專案
    - gordianknot-swager：基於 JdbcTemplate 封裝的一個簡易的 ORM 框架，也是升級版本
        - Main：無
        - Group id：org.gordianknot
        - Artifact id：gordianknot-swagger
        - Version：1.0
