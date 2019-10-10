package com.aaron.eureka_client.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/** 
 * Sleuth 支持非同步任務，除了可以用 @Async 開啟一個非同步任務，Sleuth 會為這個調用新創建一個 Span；
 * @author Aaron
 *
 */
@Configuration
@EnableAutoConfiguration
public class CustomExecutorConfig extends AsyncConfigurerSupport {

	@Autowired BeanFactory beanFactory;

	@Override 
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(7);
		executor.setMaxPoolSize(42);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("aaron-");
		executor.initialize();
		/* 
		 * 如果自訂義非同步任務執行序池，會導致無法新創一個 Span，這時候需要使用 Sleuth 提供的 LazyTraceExecutor 來包裝。
		 * 如果直接 return executor 則不會新建 Span，也就不會有 save-log 這個 Span
		 */
		return new LazyTraceExecutor(this.beanFactory, executor);
	}
}