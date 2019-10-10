package com.aaron.eureka_client.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.aaron.eureka_client.interceptor.FeignBasicAuthRequestInterceptor;

import feign.Logger;
@Configuration
public class FeignConfiguration {
    
	
	/**
	 * 日誌級別
	 */
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	
	/**
     * 創建Feign請求攔截器，在發送請求前設置認證的token,各個微服務將token設置 到環境變量中來達到通用
     *
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
}
