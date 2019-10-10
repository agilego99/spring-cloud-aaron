package com.aaron.auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 微服務之間直接調用的認證中心
 * @author aaron
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {
 	public static void main(String[] args) {
 	    SpringApplication.run(AuthApplication.class, args);
 	}
}
