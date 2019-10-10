package com.aaron.apollo_springboot.core;

import java.util.Set;

import org.springframework.core.env.EnumerablePropertySource;

/**
 * 配置類轉換成 PropertySource
 * @author Aaron
 *
 */
public class ConfigPropertySource extends EnumerablePropertySource<Config> {

	private static final String[] EMPTY_ARRAY = new String[0];

	ConfigPropertySource(String name, Config source) {
		super(name, source);
	}

	@Override
	public String[] getPropertyNames() {
		Set<String> propertyNames = this.source.getPropertyNames();
		if (propertyNames.isEmpty()) {
			return EMPTY_ARRAY;
		}
		return propertyNames.toArray(new String[propertyNames.size()]);
	}

	@Override
	public Object getProperty(String name) {
		return this.source.getProperty(name, null);
	}

}
