# Gordianknot-jdbc：集成 JdbcTemplate 
##### 框架使用： [參考範例連結](../mysql)

##### 比 jdbctemplate 有哪些優勢：
- 重新定義了 GordianknotJdbcTemplate 類，集成自 JdbcTemplate
- 不改變原始 JdbcTemplate 的功能
- 增加了 ORM 框架必備的操作物件來管理資料
- 簡單的資料庫操作使用 GordianknotJdbcTemplate 提高效率
- 支持分布式主鍵 ID 的自動生成

## 參數
- Spring boot：無
- Spring cloud：無
- Java：8
- 專案
    - gordianknot-jdbc：基於 JdbcTemplate 封裝的一個簡易的 ORM 框架，也是升級版本
        - Main：無
        - Group id：org.gordianknot
        - Artifact id：gordianknot-jdbc
        - Version：1.0.3