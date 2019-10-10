package com.aaron.eureka_client.interceptor;
import feign.RequestInterceptor;
import feign.RequestTemplate;
/**
 * Feign 請求攔截器
 *
 * @author aaron
 *
 **/
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
 	public FeignBasicAuthRequestInterceptor() {
 		
 	}
 	@Override
 	public void apply(RequestTemplate template) {
 		System.err.println("Feign請求攔截器");
 		template.header("Authorization", System.getProperty("fangjia.auth.token"));
 	}
}
