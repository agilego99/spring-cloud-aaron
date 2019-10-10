package org.gordianknot.common.anno;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
* 對 API 進行訪問速度限制

* 限制的速度值在Smconf配置中通過key關聯
* @author aaron
*
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiRateLimit {
    
    /**
     * Smconf 配置中的key
     * @return
     */
    String confKey() default "";
    
    /**
     * 速率
     * @return
     */
    int replenishRate() default 100;
    
    /**
     * 容積
     * @return
     */
    int burstCapacity() default 1000;
    
}
