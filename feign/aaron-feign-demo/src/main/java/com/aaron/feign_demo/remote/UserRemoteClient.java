package com.aaron.feign_demo.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.aaron.feign_demo.config.FeignConfiguration;

@FeignClient(value="aaron-eureka-client-user-service", configuration=FeignConfiguration.class)
public interface UserRemoteClient {

	@GetMapping("/user/hello") 
	String hello();
	
}
