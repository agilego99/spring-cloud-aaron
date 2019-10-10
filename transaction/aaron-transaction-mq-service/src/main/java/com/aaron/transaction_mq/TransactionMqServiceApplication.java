package com.aaron.transaction_mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 消息對列實現最終一致性分布式交易解決方案
 * @author aaron
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TransactionMqServiceApplication {
	public static void main(String[] args) {
		 SpringApplication.run(TransactionMqServiceApplication.class, args);
	}
}
