package com.aaron.eureka_server.utils;

/**
 * @author Aaron
 */
public class LogUtil {
	
	
	 LogUtil() {}

	public static String concat(String... ss) {
		
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
