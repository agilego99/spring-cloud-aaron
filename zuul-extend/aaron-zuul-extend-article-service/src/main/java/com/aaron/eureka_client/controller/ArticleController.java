package com.aaron.eureka_client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.eureka_client.apilimit.ApiRateLimit;
import com.aaron.eureka_client.feign.UserRemoteClient;

@RestController
public class ArticleController {
	
    @Autowired
	private UserRemoteClient userRemoteClient;
	
	@Autowired
	private HttpServletRequest request;
	
	/** 
	 * 如如未配置過 Key：open.api.callHello 
	 * 1.使用前需先至 Apollo 配置中心設置（http://localhost:8070）設定 改組 Key：Value 
	 * 2.open.api.callHello：10
	 * 3.然後重啟此應用服務
	 * @return
	 */
	@ApiRateLimit(confKey = "open.api.callHello")
	@GetMapping("/article/callHello")
	public String callHello() {
	    /**
	     * 接收 Zuul 傳遞過來的參數
	     * 具體方式可以通過 request 物件獲得傳遞過來的用戶資訊
	     */
		System.err.println("用户ID:" + request.getHeader("uid"));
	    return userRemoteClient.hello();
	}
}
