package com.aaron.feign_demo.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aaron.feignapi.user.User;
import com.aaron.feignapi.user.UserRemoteClient;

@RestController
public class DemoController implements UserRemoteClient {

	@Override
	public String getName() {
		return "aaron";
	}

	@Override
	public String getUserInfo(@RequestParam("name")String name) {
		return name;
	}

	@Override
	public String getUserDetail(@RequestParam Map<String, Object> param) {
		System.err.println(param.toString());
		return param.get("name").toString();
	}

	@Override
	public String addUser(@RequestBody User user) {
		return user.getName();
	}

}
