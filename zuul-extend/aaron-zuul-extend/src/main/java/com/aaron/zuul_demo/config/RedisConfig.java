package com.aaron.zuul_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * The type Redis config.
 */
@Configuration
public class RedisConfig {

    /**
     * Redis template redis template.
     *
     * @param jedisConnectionFactory the jedis connection factory
     * @return the redis template
     */
// RedisTemplate 配置
    @Bean(name = "longRedisTemplate")
    public RedisTemplate<String, Long> redisTemplate(RedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Long> template = new RedisTemplate<String, Long>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer< Long >( Long.class ) );
        template.setValueSerializer(new GenericToStringSerializer< Long >( Long.class ) );
        return template;
    }
}
