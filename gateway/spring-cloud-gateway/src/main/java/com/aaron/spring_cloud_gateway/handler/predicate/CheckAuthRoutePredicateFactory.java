package com.aaron.spring_cloud_gateway.handler.predicate;

import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
//命名時需要以 RoutePredicateFactory 結尾，在使用的時候 「CheckAuth」就是這個路由斷言工廠的名稱
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

	public CheckAuthRoutePredicateFactory() {
		super(Config.class);
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return exchange -> {
			System.err.println("進入了 CheckAuthRoutePredicateFactory\t" + config.getName());
			if (config.getName().equals("aaron")) {
				return true;
			}
			return false;
		};
	}

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
