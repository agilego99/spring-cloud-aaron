package org.gordianknot.conf.client;
import org.gordianknot.conf.client.init.ConfInit;
public class ConfApplication {
 	
 	/**
 	* 類（Biz.class）
 	* @param clazz
 	* @return
 	*/
 	@SuppressWarnings("unchecked")
 	public static <T> T getBean(Class<T> clazz) {
 		return (T)ConfInit.getConfData(clazz.getName());
 	}
 	
 	/**
 	* 類的全稱（org.gordianknot.conf.demo.conf.Biz）
 	* @param className
 	* @return
 	*/
 	@SuppressWarnings("unchecked")
 	public static <T> T getBean(String className) {
 		return (T)ConfInit.getConfData(className);
 	}
 	
 	/**
 	* 獲取配置Bean
 	* @author aaron
 	* @param env     環境
 	* @param system  系統
 	* @param confFileName  配置名稱
 	* @return
 	*/
 	public static <T> T getBean(String env, String system, String confFileName) {
 		StringBuilder key = new StringBuilder(env);
 		key.append("/");
 		key.append(system);
 		key.append("/");
 		key.append(confFileName);
 		String className = ConfInit.getConfBeanClass(key.toString());
 		return getBean(className);
 	}
}
