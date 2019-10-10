package com.aaron.eureka_client.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.aaron.eureka_client.interceptor.TokenInterceptor;

/**
 * 配置 RestTemplate 攔截器
 * @author Aaron
 *
 */
@Configuration
public class BeanConfiguration {

	@Autowired
	private TokenInterceptor tokenInterceptor;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Collections.singletonList(tokenInterceptor));
		return restTemplate;
	}
}
