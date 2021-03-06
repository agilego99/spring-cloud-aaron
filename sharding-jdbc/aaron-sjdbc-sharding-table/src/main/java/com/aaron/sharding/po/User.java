package com.aaron.sharding.po;

import java.io.Serializable;
import org.gordianknot.jdbc.annotation.AutoId;
import org.gordianknot.jdbc.annotation.Field;
import org.gordianknot.jdbc.annotation.TableName;

@TableName(value = "user", author = "aaron", desc = "用户表")
public class User implements Serializable {

	private static final long serialVersionUID = -1205226416664488559L;
	
	@Field(value="id", desc="ID")
	private Long id;

	@Field(value="city", desc="城市")
	private String city = "";
	
	@Field(value="name", desc="姓名")
	private String name = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
