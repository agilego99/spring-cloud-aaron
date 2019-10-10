package com.aaron.zuul_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul整合Swagger示列
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-07-12
 * 
 */
@EnableZuulProxy
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}