package com.aaron.apollo_springboot.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/**
 * 配置初始化邏輯
 * @author Aaron
 * EnvironmentAware 接口是為了獲取 Environment 物件。
 */
@Component
public class PropertySourcesProcessor implements BeanFactoryPostProcessor, EnvironmentAware {

	String APOLLO_PROPERTY_SOURCE_NAME = "ApolloPropertySources";
	 
	private ConfigurableEnvironment environment;
	 
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 啓動時初始化配置到Spring PropertySource
		Config config = new Config();
		ConfigPropertySource configPropertySource = new ConfigPropertySource("application", config);
		
		CompositePropertySource composite = new CompositePropertySource(APOLLO_PROPERTY_SOURCE_NAME);
        composite.addPropertySource(configPropertySource);
        
        environment.getPropertySources().addFirst(composite);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = (ConfigurableEnvironment) environment;
	}
}
