package com.aaron.auth.service;

import com.aaron.auth.po.User;
import com.aaron.auth.query.AuthQuery;

public interface AuthService {

	User auth(AuthQuery query);
	
}
