package com.aaron.eureka_client.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aaron.eureka_client.apilimit.ApiLimitAspect;
import com.aaron.eureka_client.feign.FeignBasicAuthRequestInterceptor;
import com.aaron.eureka_client.filter.HttpHeaderParamFilter;

@Configuration
public class BeanConfig {
	
	// 註冊傳遞參數過濾器
	@Bean
	public FilterRegistrationBean<HttpHeaderParamFilter> filterRegistrationBean() {
		FilterRegistrationBean<HttpHeaderParamFilter> registrationBean = new FilterRegistrationBean<HttpHeaderParamFilter>();
		HttpHeaderParamFilter httpHeaderParamFilter = new HttpHeaderParamFilter();
		registrationBean.setFilter(httpHeaderParamFilter);
		List<String> urlPatterns = new ArrayList<String>(1);
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	@Bean
	public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new FeignBasicAuthRequestInterceptor();
	}
	
	@Bean
	public ApiLimitAspect apiLimitAspect() {
		return new ApiLimitAspect();
	}
}
