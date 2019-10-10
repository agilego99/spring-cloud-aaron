package com.aaron.cache_data_redis.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class CacheAutoConfiguration extends CachingConfigurerSupport {
 	private Logger logger = LoggerFactory.getLogger(CacheAutoConfiguration.class);
 	/**
    * 透過以下的處理，即使 Redis 發生異常，系統連接不上也會因此影影響業務功能，而是會繼續查詢資料庫相關操作。
 	* Redis 數據操作異常處理 這裡的處理：在日誌中打印出錯誤信息，但是放行
 	* 保證 Redis 服務器出現連接等問題的時候不影響程序的正常運行，使得能夠出問題時不用緩存,繼續執行業務邏輯去查詢DB 
 	*/
 	@Bean
 	public CacheErrorHandler errorHandler() {
 		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
 			@Override
 			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
 				logger.error("redis異常：key=[{}]", key, e);
 			}
 			@Override
 			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
 				logger.error("redis異常：key=[{}]", key, e);
 			}
 			@Override
 			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
 				logger.error("redis異常：key=[{}]", key, e);
 			}
 			@Override
 			public void handleCacheClearError(RuntimeException e, Cache cache) {
 				logger.error("redis異常：", e);
 			}
 		};
 		return cacheErrorHandler;
 	}
}
