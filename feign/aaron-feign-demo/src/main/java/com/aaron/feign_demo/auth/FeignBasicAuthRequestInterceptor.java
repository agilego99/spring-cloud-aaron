package com.aaron.feign_demo.auth;


import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
	
	public FeignBasicAuthRequestInterceptor() {
		
	}

	public void apply(RequestTemplate template) {
		// 業務邏輯
		System.err.println("進入攔截器了");
	}	
}
