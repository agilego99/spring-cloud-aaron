package org.gordianknot.jdbc;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 本地緩存信息類
 */
public class CacheData {
 	/**
 	* PO中屬性名稱與數據庫字段名稱的緩存映射信息
 	* key:org.gordianknot.model.ld.po.LouDong.ld_num
 	* value:ldNum
 	*/
 	private static Map<String, String> fieldNameMappingMap = new ConcurrentHashMap<String, String>();
 	
 	public static void put(String key, String value) {
 		fieldNameMappingMap.put(key, value);
 	}
 	
 	public static String get(String key) {
 		return fieldNameMappingMap.get(key);
 	}
 	
}
