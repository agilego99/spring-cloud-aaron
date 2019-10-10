package com.aaron.eureka_client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "aaron-zuul-extend-user-service") 
public interface UserRemoteClient {
	
   @GetMapping("/user/hello") 
   String hello(); 
}