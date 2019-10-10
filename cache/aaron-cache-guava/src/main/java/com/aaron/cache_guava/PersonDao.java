package com.aaron.cache_guava;

public class PersonDao {

	Person findById(String id) {
		System.err.println("From DB\t"+"query id:" + id);
		Person p = new Person();
		p.setName("gordianknot");
		return p;
	}
}
