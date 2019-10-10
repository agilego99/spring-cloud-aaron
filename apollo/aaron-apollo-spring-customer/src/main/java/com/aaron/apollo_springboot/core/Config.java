package com.aaron.apollo_springboot.core;

import java.util.HashSet;
import java.util.Set;

/**
 * 配置獲取類
 * @author Aaron
 *
 */
public class Config {
	
	public String getProperty(String key, String defaultValue) {
		if (key.equals("gordianknotName")) {
			return "戈迪安繩結";
		}
		return null;
	}

	public Set<String> getPropertyNames() {
		Set<String> names = new HashSet<>();
		names.add("gordianknotName");
		return names;
	}
}
