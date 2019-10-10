package com.aaron.apollo_springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 自定 Apollo 配置类
 * @author aaron
 *
 */
@Data
@Configuration
public class UserConfig {

	@Value("${username:aaron}")
	private String username;	
}
