package com.aaron.eureka_client.apilimit;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 對 API 進行訪問速度限制 
 * 限制的速度值在 Apollo 配置中通過 key 關聯
 * @author aaron
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiRateLimit {
 	/**
 	* Apollo 配置中的 key
 	* @return
 	*/
 	String confKey();
}
