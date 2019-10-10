package com.aaron.feign_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Feign示列
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-07-28
 * 
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.aaron.feignapi"})
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}