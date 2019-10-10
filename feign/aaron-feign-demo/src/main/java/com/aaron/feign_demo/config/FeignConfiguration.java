package com.aaron.feign_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aaron.feign_demo.auth.FeignBasicAuthRequestInterceptor;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfiguration {
	/**
	 * 日誌級別
	 * 
	 * @return
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password");
	}

	@Bean
	public FeignBasicAuthRequestInterceptor feignBasicAuthRequestInterceptor() {
		return new FeignBasicAuthRequestInterceptor();
	}

	@Bean
	public Request.Options options() {
		return new Request.Options(5000, 10000);
	}
	
	
//	@Bean
//	public Contract feigncontract() {
//		return new feign.Contract.Default();
//	}

}
