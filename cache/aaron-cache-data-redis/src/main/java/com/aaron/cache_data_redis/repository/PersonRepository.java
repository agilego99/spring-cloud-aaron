package com.aaron.cache_data_redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aaron.cache_data_redis.po.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

}