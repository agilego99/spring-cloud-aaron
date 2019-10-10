package com.aaron.sjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.sjdbc.po.User;
import com.aaron.sjdbc.service.UserService;
import com.dangdang.ddframe.rdb.sharding.api.HintManager;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public Object list() {
		return userService.list();
	}
	
	@GetMapping("/add")
	public Object add() {
		User user = new User();
		user.setCity("屏東");
		user.setName("Reann");
		return userService.add(user);
	}
	
}
