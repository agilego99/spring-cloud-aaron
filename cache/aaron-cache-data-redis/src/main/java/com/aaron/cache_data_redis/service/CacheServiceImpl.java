package com.aaron.cache_data_redis.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.aaron.cache_data_redis.config.CacheAutoConfiguration;

/**
 *  自定義緩存工具類
 *  以程式碼層面做緩存控制，取代 Sping Cache 常用的註解方式（如 @Cacheable ...）
 * @author Aaron
 */

@Service
public class CacheServiceImpl implements CacheService {
	private Logger logger = LoggerFactory.getLogger(CacheAutoConfiguration.class);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private long timeout = 1L;

	private TimeUnit timeUnit = TimeUnit.HOURS;

	@Override
	public void setCache(String key, String value, long timeout, TimeUnit timeUnit) {
		try {
			stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
		} catch (Exception e) {
			logger.error("",e);
		}
	}

	@Override
	public String getCache(String key) {
		try {
			return stringRedisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}

	@Override
	public void deleteCache(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public <V, K> String getCache(K key, Closure<V, K> closure) {
		return doGetCache(key, closure, this.timeout, this.timeUnit);
	}

	@Override
	public <V, K> String getCache(K key, Closure<V, K> closure, long timeout, TimeUnit timeUnit) {
		return doGetCache(key, closure, timeout, timeUnit);
	}

	private <K, V> String doGetCache(K key, Closure<V, K> closure, long timeout, TimeUnit timeUnit) {
		String ret = getCache(key.toString());
		if (ret == null) {
			Object r = closure.execute(key);
			setCache(key.toString(), r.toString(), timeout, timeUnit);
			return r.toString();
		}
		return ret;
	}
}