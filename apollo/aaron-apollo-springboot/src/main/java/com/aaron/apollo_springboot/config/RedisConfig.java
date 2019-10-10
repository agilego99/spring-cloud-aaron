package com.aaron.apollo_springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import lombok.Data;

@Data
@Configuration
// 使用 ConfigurationProperties 方式配置有個缺陷，當配置的值發生變化時不會自動刷新，而需要手動執行刷新邏輯。建議不要使用此種方式。
// 如果配置需要統一加前綴的方式可以用 Java Config 的方式代替。
@ConfigurationProperties(prefix = "redis.cache")
public class RedisConfig {
    // 配置中心只需要增加 「redis.cache.host = 192.168.100.1」 即可實現注入
	private String host;
}
