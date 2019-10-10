package com.aaron.feign_demo.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//fallback=UserRemoteClientFallback.class
@FeignClient(value="aaron-eureka-client-user-service", fallbackFactory=UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {

	@GetMapping("/user/hello") 
	String hello();
	
}


