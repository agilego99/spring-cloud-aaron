package org.gordianknot.jdbc.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 字段名
 * @author aaron
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
public @interface Field {
 	
 	/**
 	* 字段名稱
 	* @return
 	*/
 	String value();
 	
 	/**
 	* 字段描述
 	* @return
 	*/
 	String desc();
 	
}
