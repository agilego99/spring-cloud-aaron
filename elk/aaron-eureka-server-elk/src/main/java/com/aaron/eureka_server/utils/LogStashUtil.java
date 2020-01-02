package com.aaron.eureka_server.utils;

import org.springframework.stereotype.Component;

/**
 * @author Aaron
 */
public class LogStashUtil {
	
	public static final String LOGSTASH_NAME1 = "logstash_name1";
	public static final String LOGSTASH_NAME2 = "logstash_name2";
	

	public  static String concat(String... ss) {
		
		StringBuilder sb = new StringBuilder();
		
		for (String s : ss) {
			sb.append(str(s) + " ");
		}
		return sb.toString();
	}
	
	public static String str(Object o) {
		return (o == null) ? "" : String.valueOf(o).trim();
	}

	public static String str(String s) {
		if (s == null)
			return "";
		return s.trim();
	}

}
