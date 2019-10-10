package com.aaron.cache_data_redis.service;

import com.aaron.cache_data_redis.po.Person;

public interface PersonService {
	public Person get(String id);
	public Person get2(String id);
}
