package com.aaron.eureka_client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**  
 * 認證服務API調用客戶端   
 * @author aaron  
 * 
 **/ 
@FeignClient(value = "aaron-auth-user-service") 
public interface UserRemoteClient {
	
   @GetMapping("/user/hello") 
   String hello(); 
   
}