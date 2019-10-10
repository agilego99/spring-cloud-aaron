package com.aaron.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 啟用 Swagger
 * @author Aaron
 */
@EnableDiscoveryClient
@EnableSwagger2Doc
@SpringBootApplication
public class AuthApplication {
	public static void main(String[] args) {
	    SpringApplication.run(AuthApplication.class, args);
	}
}
