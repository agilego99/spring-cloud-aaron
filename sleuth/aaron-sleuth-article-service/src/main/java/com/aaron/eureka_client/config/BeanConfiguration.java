package com.aaron.eureka_client.config;

import java.util.regex.Pattern;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.instrument.web.ServerSampler;
import org.springframework.cloud.sleuth.instrument.web.SkipPatternProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import brave.http.HttpAdapter;
import brave.http.HttpSampler;

@Configuration
public class BeanConfiguration {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * 對於某些請求不想開啟跟蹤，可以透過 HttpSampler 來過濾掉，比如 Swagger 這些請求等。
	 * @param provider
	 * @return
	 */
	@Bean(name = ServerSampler.NAME)
	HttpSampler myHttpSampler(SkipPatternProvider provider) {
		Pattern pattern = provider.skipPattern();
		return new HttpSampler() {

			// 過濾器核心方法
			@Override
			public <Req> Boolean trySample(HttpAdapter<Req, ?> adapter, Req request) {
				String url = adapter.path(request);
				boolean shouldSkip = pattern.matcher(url).matches();
				if (shouldSkip) {
					return false;
				}
				return null;
			}
		};
	}
}
