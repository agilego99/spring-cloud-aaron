package com.aaron.apollo_springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.apollo_springboot.config.RedisConfig;
import com.aaron.apollo_springboot.config.Student;
import com.aaron.apollo_springboot.config.UserConfig;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;

@RestController
public class ConfigController {

	// Config 配置注入類
	@Autowired
	private UserConfig userConfig;
	
	/**
	 * Placeholder 注入配置方式
	 * 用戶名，默認值為 aaron
	 */
	@Value("${username:aaron}")
	private String username;
	
	@Autowired
	private RedisConfig redisConfig;
	
	// 用來自動注入 Apollo Cofig 對象
	@ApolloConfig
	private Config config;
	
	/** 用來把配置的 JSON 字串自動注入為對象；須定義實體類 Student
     *  配置中心配置的內容如下：
     *  stus = [{"id":1,"name":"jason"}]
     */
	@ApolloJsonValue("${stus:[]}")
	private List<Student> stus;
	
	@GetMapping("/config/stus")
	public List<Student> stus() {
		return stus;
	}
	
	@GetMapping("/config/getUserName3")
	public String getUserName3() {
		return config.getProperty("username", "aaron");
	}
	
	@GetMapping("/config/getUserName")
	public String getUserName() {
		return username;
	}
	
	@GetMapping("/config/getUserName2")
	public String getUserName2() {
		return userConfig.getUsername();
	}
		
	@GetMapping("/config/getRedisConfig")
	public String getRedisConfig() {
		return redisConfig.getHost();
	}
	
	// 用來自動註冊 ConfigChangeListener，以監聽監聽配置變化
	@ApolloConfigChangeListener
	private void someOnChange(ConfigChangeEvent changeEvent) {
		
		if(changeEvent.isChanged("username")) {
		    System.out.println("username 發生修改了");
		}
		
		if(changeEvent.isChanged("redis.cache.host")) {
		    System.out.println("redis.cache.host 發生修改了");
		}
	}
}
