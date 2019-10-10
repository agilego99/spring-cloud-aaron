package com.aaron.feign_demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.feignapi.user.User;
import com.aaron.feignapi.user.UserRemoteClient;


@RestController
public class DemoController {

	@Autowired
	private UserRemoteClient userRemoteClient;

	@GetMapping("/call")
	public String callHello() {
		String result = userRemoteClient.getName();
		System.out.println("getName調用結果：" + result);
		
		result = userRemoteClient.getUserInfo("aaron");
		System.out.println("getUserInfo調用結果：" + result);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "aaron2");
		result = userRemoteClient.getUserDetail(param);
		System.out.println("getUserDetail調用結果：" + result);
		
		User user = new User();
		user.setName("aaron3");
		result = userRemoteClient.addUser(user);
		System.out.println("addUser調用結果：" + result);
		return result;
	}
	
}
