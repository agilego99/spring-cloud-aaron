package com.aaron.sharding.service;

import java.util.List;

import com.aaron.sharding.po.User;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
	User findById(Long id);
	
	User findByName(String name);
	
}
