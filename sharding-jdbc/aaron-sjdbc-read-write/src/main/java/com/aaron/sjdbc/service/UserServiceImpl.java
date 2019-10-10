package com.aaron.sjdbc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.gordianknot.jdbc.EntityService;

import com.aaron.sjdbc.po.User;
import com.dangdang.ddframe.rdb.sharding.api.HintManager;

@Service
public class UserServiceImpl extends EntityService<User> implements UserService {
	
	public List<User> list() {
        // 强制路由主資庫
		HintManager.getInstance().setMasterRouteOnly();
		return super.list();
	}

	public Long add(User user) {
		return (Long) super.save(user);
	}

}
