package com.aaron.transaction_mq.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.gordianknot.jdbc.GordianknotTemplate;

@Configuration
public class BeanConfig {
	
	/**
	 * JDBC
	 * @return
	 */
	@Bean(autowire=Autowire.BY_NAME)
	public GordianknotTemplate gordianknotTemplate() {
		return new GordianknotTemplate("com.aaron.transaction_mq.po");
	}
	
}
