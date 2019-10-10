package com.aaron.eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.spring4all.swagger.EnableSwagger2Doc;


/**
 * Eureka客户端示列
 * @author aaron
 * @date 2018-08-26
 */
@EnableDiscoveryClient
@EnableSwagger2Doc
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}