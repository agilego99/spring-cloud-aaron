package com.aaron.feign_demo;

import feign.Feign;

public class RestApiCallUtils {
	/**
	 * 獲取 API 接口代理對象
	 * 
	 * @param apiType 接口類
	 * @param url     API 地址
	 * @return
	 */
	public static <T> T getRestClient(Class<T> apiType, String url) {
		return Feign.builder().target(apiType, url);
	}

}
