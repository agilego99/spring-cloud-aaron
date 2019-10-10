package org.gordianknot.conf.springboot.demo;
import org.gordianknot.conf.client.init.ConfInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class App {
 	
 	//可以使用Bean的創建方式來初始化配置客戶端
 	@Bean
 	public ConfInit confInit() {
 		return new ConfInit();
 	}
 	
 	public static void main(String[] args) {
 		SpringApplication.run(App.class, args);
 	}
}
