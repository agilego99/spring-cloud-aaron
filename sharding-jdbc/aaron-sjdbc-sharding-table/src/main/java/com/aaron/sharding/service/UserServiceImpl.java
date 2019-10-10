package com.aaron.sharding.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.aaron.sharding.po.User;

import org.gordianknot.jdbc.EntityService;

@Service
public class UserServiceImpl extends EntityService<User> implements UserService {

	public List<User> list() {
		return super.list();
	}

	public Long add(User user) {
		return (Long) super.save(user);
	}

	@Override
	public User findById(Long id) {
		return super.getById("id", id);
	}

	@Override
	public User findByName(String name) {
		return super.getById("name", name);
	}

}
