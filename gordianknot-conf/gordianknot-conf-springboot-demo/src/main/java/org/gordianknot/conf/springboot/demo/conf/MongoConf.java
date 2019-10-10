package org.gordianknot.conf.springboot.demo.conf;
import java.util.HashMap;
import java.util.Map;

import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;
@GordianknotConf(system = "gordianknot-conf-springboot-demo", env = true, prefix = "spring.data.mongodb")
public class MongoConf {
    
	// 認證資料庫；採用帳密登入時必須要加入
    @ConfField("認證資料庫名稱")
    private String authentication = "admin";
	
	@ConfField("資料庫名稱")
    private String database = "test";
    
    @ConfField("資料庫地址")
    private String host = "gordianknot";
    
    @ConfField("資料庫端口")
    private int port = 27017;
    
    // 使用認證時需要
    @ConfField("資料庫帳號") 
    private String username = "admin";
    
    // 使用認證時需要
    @ConfField("資料庫密碼") 
    private String password = "999999";
    
    @ConfField("資料庫其他參數")
    private Map<String, Object> map = new HashMap<String, Object>(){{
        put("maxSize", 100);
    }};
    
    public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
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
	public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    public Map<String, Object> getMap() {
        return map;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
}
