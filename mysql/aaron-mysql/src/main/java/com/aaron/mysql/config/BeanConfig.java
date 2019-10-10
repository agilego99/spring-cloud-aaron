package com.aaron.mysql.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.gordianknot.jdbc.GordianknotTemplate;

@Configuration
public class BeanConfig {
	/**
	 * JDBC
	 */
	@Bean(autowire = Autowire.BY_NAME)
	public GordianknotTemplate gordianknotTemplate() {
		return new GordianknotTemplate("com.aaron.mysql.po");
	}

}
