package com.aaron.fsh.house.conf;
import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.core.SmconfUpdateCallBack;
import org.gordianknot.conf.client.core.rest.Conf;
import org.springframework.cloud.context.refresh.ContextRefresher;
import com.aaron.fsh.house.FshHouseServiceApplication;
/**
 * 資料庫連接池配置
 *
 * @author aaron
 **/
@GordianknotConf(system = "aaron-fsh-house-service", env = true, prefix = "spring.datasource.druid")
public class MysqlDataSourceConf implements SmconfUpdateCallBack {
    @ConfField("連接地址")
    private String url = "jdbc:mysql://gordianknot:3306/aaron_fsh";
    @ConfField("數據庫用戶名")
    private String username = "root";
    @ConfField("數據庫密碼")
    private String password = "999999";
    @ConfField("數據庫驅動")
    private String driverClassName = "com.mysql.jdbc.Driver";
    @ConfField("初始化連接數")
    private int initialSize = 10;
    @ConfField("最小連接數")
    private int minIdle = 5;
    @ConfField("最大連接數")
    private int maxActive = 50;
    @ConfField("獲取連接時最大等待時間，單位毫秒")
    private int maxWait = 60000;
    @ConfField("Destroy線程會檢測連接的間隔時間")
    private int timeBetweenEvictionRunsMillis = 60000;
    @ConfField("minEvictableIdleTimeMillis")
    private int minEvictableIdleTimeMillis = 60000;
    @ConfField("檢測連接是否有效的sql")
    private String validationQuery = "SELECT 1 FROM DUAL";
    @ConfField("檢測連接是否有效")
    private String testWhileIdle = "true";
    @ConfField("申請連接時執行validationQuery檢測連接是否有效")
    private String testOnBorrow = "false";
    @ConfField("歸還連接時執行validationQuery檢測連接是否有效")
    private String testOnReturn = "false";
    @ConfField("定時輸出統計日誌的世界，毫秒")
    private int timeBetweenLogStatsMillis = 300000;
    @ConfField("是否緩存preparedStatement，也就是PSCache")
    private String poolPreparedStatements = "true";
    @ConfField("Statement緩存大小")
    private int maxPoolPreparedStatementPerConnectionSize = 20;
    @ConfField("filters")
    private String filters = "stat,wall,log4j";
    @ConfField("連接屬性")
    private String connectionProperties = "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=1000";
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getMaxActive() {
        return maxActive;
    }
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDriverClassName() {
        return driverClassName;
    }
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    public int getInitialSize() {
        return initialSize;
    }
    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }
    public int getMinIdle() {
        return minIdle;
    }
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
    public int getMaxWait() {
        return maxWait;
    }
    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }
    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }
    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }
    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }
    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
    public String getValidationQuery() {
        return validationQuery;
    }
    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }
    public String getTestWhileIdle() {
        return testWhileIdle;
    }
    public void setTestWhileIdle(String testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }
    public String getTestOnBorrow() {
        return testOnBorrow;
    }
    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
    public String getTestOnReturn() {
        return testOnReturn;
    }
    public void setTestOnReturn(String testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
    public int getTimeBetweenLogStatsMillis() {
        return timeBetweenLogStatsMillis;
    }
    public void setTimeBetweenLogStatsMillis(int timeBetweenLogStatsMillis) {
        this.timeBetweenLogStatsMillis = timeBetweenLogStatsMillis;
    }
    public String getPoolPreparedStatements() {
        return poolPreparedStatements;
    }
    public void setPoolPreparedStatements(String poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }
    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }
    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }
    public String getFilters() {
        return filters;
    }
    public void setFilters(String filters) {
        this.filters = filters;
    }
    public String getConnectionProperties() {
        return connectionProperties;
    }
    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }
    @Override
    public void reload(Conf conf) {
        try {
            new ContextRefresher(FshHouseServiceApplication.context, null).refresh();
        } catch (NullPointerException e) {}
    }
}
