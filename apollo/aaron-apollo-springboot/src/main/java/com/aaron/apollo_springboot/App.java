package com.aaron.apollo_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Apollo整合Spring Boot示列
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-7-2
 * 
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		// 指定環境
		System.setProperty("env", "DEV");
		SpringApplication.run(App.class, args);
	}
}
