package com.aaron.springcloud_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HellController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/callHello")
	/* 
	 用於指定依賴服務調用延遲或失敗調用的方法
     測試；可於第二次執行時將 @HystrixCommand 取消，然後在執行一次將出現 500 錯誤
    */
	@HystrixCommand(fallbackMethod = "defaultCallHello", commandProperties = {                  
			@HystrixProperty(
					name="execution.isolation.strategy",      
					value = "THREAD")    
			} 
	)
	public String callHello() {
		String result = restTemplate.getForObject("http://localhost:8088/house/hello", String.class);
		return result;
	}
    // 回退方法定義
	public String defaultCallHello() {
		return "fail";
	}
}