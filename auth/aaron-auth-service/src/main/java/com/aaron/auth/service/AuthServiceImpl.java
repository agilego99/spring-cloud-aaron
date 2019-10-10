package com.aaron.auth.service;

import org.springframework.stereotype.Service;

import com.aaron.auth.po.User;
import com.aaron.auth.query.AuthQuery;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public User auth(AuthQuery query) {
		return new User(1L);
	}
}
