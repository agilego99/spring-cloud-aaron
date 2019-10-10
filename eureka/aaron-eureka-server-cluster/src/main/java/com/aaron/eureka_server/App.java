package com.aaron.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Eureka 服務端集群示列
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-06-26
 * 
 */
@EnableEurekaServer
@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	// 關閉 csrf
	        http.csrf().disable();
	        // 支持 httpBasic
	        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	    }
	}
}
