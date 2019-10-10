package com.aaron.ribbon_eureka_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Ribbon 重試範例
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-06-25
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}