package com.aaron.zuul_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aaron.zuul_demo.filter.AuthFilter;
import com.aaron.zuul_demo.filter.DownGradeFilter;
import com.aaron.zuul_demo.filter.GrayPushFilter;
import com.aaron.zuul_demo.filter.LimitFilter;

@Configuration
public class FilterConfig {

	// 啟用認證過濾器
	@Bean
	public AuthFilter authFilter() {
		return new AuthFilter();
	}
	
	// 啟用限流過濾器
	@Bean
	public LimitFilter limitFilter() {
		return new LimitFilter();
	}
	
	// 啟用服務降級
	@Bean
	public DownGradeFilter downGradeFilter() {
		return new DownGradeFilter();
	}
	
	@Bean
	public GrayPushFilter grayPushFilter() {
		return new GrayPushFilter();
	}
}
