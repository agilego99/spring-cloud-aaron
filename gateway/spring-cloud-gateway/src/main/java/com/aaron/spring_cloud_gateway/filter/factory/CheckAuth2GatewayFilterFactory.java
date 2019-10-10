package com.aaron.spring_cloud_gateway.filter.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class CheckAuth2GatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuth2GatewayFilterFactory.Config>{
	public CheckAuth2GatewayFilterFactory() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			System.err.println("進入了 CheckAuth2GatewayFilterFactory" + config.getName());
			ServerHttpRequest request = exchange.getRequest().mutate()
					.build();

			return chain.filter(exchange.mutate().request(request).build());
		};
	}
	
	// 配置 Config
	public static class Config {
		
		private String name;
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}
}
