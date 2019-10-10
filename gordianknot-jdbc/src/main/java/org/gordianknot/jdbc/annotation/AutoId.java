package org.gordianknot.jdbc.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
* 標示此字段為主鍵，需要動態生成分布式主鍵ID
* 如果要用數據庫默認的自增就不要加此註解
* 加了此註解，字段必須是String或者Long
* @author aaron
*/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface AutoId {
}
