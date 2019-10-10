package com.aaron.cache_data_redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.aaron.cache_data_redis.po.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	// #id 是 SpEL 語法，通過參數名來定義緩存 Key
	@Cacheable(value = "get", key = "#id")
	public Person get(String id) {
		Person p = new Person();
		p.setFirstname("Aaron");
		p.setLastname("Chu");
		p.setId("111");
		return p;
	}
	
	// 透過 KeyGenerator 自動生成緩存的 Key，指定 keyGenerator 就不需要配置 Key
	@Cacheable(value = "get2", keyGenerator = "keyGenerator")
	public Person get2(String id) {
		Person p = new Person();
		p.setFirstname("Ray");
		p.setLastname("Chu");
		p.setId("222");
		return p;
	}
}

