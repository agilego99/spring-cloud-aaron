package com.aaron.eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Eureka客户端示列
 * @author aaron
 * @date 2018-08-26
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		// 指定環境（開發演示用，不能用於生產環境）
		System.setProperty("env", "DEV");
		SpringApplication.run(App.class, args);
	}
}