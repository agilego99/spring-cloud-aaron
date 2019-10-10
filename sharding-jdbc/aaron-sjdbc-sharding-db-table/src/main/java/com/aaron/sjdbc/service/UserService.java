package com.aaron.sjdbc.service;

import java.util.List;

import com.aaron.sjdbc.po.User;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
	User findById(Long id);
	
	User findByName(String name);
	
}
