package org.gordianknot.conf.client.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;
/**
* 標識 Bean 為配置文件的註解
* @author aaron
* @date 2019-06-20
*/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface GordianknotConf {
    
    /**
     * 配置的名稱, 不填默認為類名
     * @return
     */
    String value() default "";
    
    /**
     * 系統名稱, 標識在哪個系統使用此配置
     * @return
     */
    String system() default "";
    
    /**
     * 是否設置到環境變量中
     * @author aaron
     * @return
     */
    boolean env() default false;
    
    /**
     * key的前綴,只針對需要設置到環境變量中的值有作用
     * @author aaron
     * @return
     */
    String prefix() default "";
}