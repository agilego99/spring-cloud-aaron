package com.aaron.mqtask;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
@EnableDiscoveryClient
// 開啟 Feign 
@EnableFeignClients(basePackages = "com.aaron.mqclient")
@SpringBootApplication
public class TransactionTaskApplication {
 	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTaskApplication.class);
 	
 	public static void main(String[] args) {
 		SpringApplication application = new SpringApplication(TransactionTaskApplication.class);
 		ConfigurableApplicationContext content = application.run(args);
        try {
        	// 負責發送消息的處理類
        	ProcessMessageTask task = content.getBean(ProcessMessageTask.class);
        	task.start();
        	new CountDownLatch(1).await();
 		} catch (InterruptedException e) {
 			LOGGER.error("專案啓動異常", e);
 		}
 	}
}