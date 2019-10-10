package com.aaron.cache_data_redis.service;
import java.util.concurrent.TimeUnit;

/**
 *  自定義緩存工具類
 *  以程式碼層面做緩存控制，取代 Sping Cache 常用的註解方式（如 @Cacheable ...）
 * @author Aaron
 */
public interface CacheService {
 	/**
 	* 設置緩存
 	* @param key      緩存 KEY
 	* @param value    緩存值
 	* @param timeout  緩存過期時間
 	* @param timeUnit 緩存過期時間單位
 	*/
 	public void setCache(String key, String value, long timeout, TimeUnit timeUnit);
 	/**
 	* 獲取緩存
 	* @param key 緩 存 KEY
 	* @return
 	*/
 	public String getCache(String key);
 	public <V, K> String getCache(K key, Closure<V, K> closure);
 	public <V, K> String getCache(K key, Closure<V, K> closure, long timeout, TimeUnit timeUnit);
 	/**
 	* 刪除緩存
 	* @param key 緩 存 KEY
 	*/
 	public void deleteCache(String key);
}
