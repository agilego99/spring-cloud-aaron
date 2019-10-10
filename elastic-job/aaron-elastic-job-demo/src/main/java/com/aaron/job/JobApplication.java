package com.aaron.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Elastic Job XML配置示列
 * @author aaron
 * @date 
 */
@SpringBootApplication
/** 
 * 透過 @ImportResource 導入自定義的 xml 文件；此為 Spring Boot 中為了兼容性而特別保留的一個功能
 */
@ImportResource(locations = { "classpath:applicationContext.xml" })
public class JobApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}
}
