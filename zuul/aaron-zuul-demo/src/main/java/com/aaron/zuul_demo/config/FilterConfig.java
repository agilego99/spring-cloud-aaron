package com.aaron.zuul_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aaron.zuul_demo.filter.DebugRequestFilter;
import com.aaron.zuul_demo.filter.ErrorFilter;
import com.aaron.zuul_demo.filter.IpFilter;

@Configuration
public class FilterConfig {

	@Bean
	public IpFilter ipFilter() {
		return new IpFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public DebugRequestFilter debugRequestFilter() {
		return new DebugRequestFilter();
	}
}
