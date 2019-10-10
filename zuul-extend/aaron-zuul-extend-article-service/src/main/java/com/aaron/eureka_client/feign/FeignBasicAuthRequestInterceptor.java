package com.aaron.eureka_client.feign;

import java.util.Map;

import com.aaron.eureka_client.support.RibbonFilterContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;
/**
 * 傳遞用戶資訊到被調用的服務
 * Feign 攔截器設置參數到請求頭中
 * @author aaron
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
	public FeignBasicAuthRequestInterceptor() {

	}

	@Override
	public void apply(RequestTemplate template) {
		Map<String, String> attributes = RibbonFilterContextHolder.getCurrentContext().getAttributes();
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			template.header(key, value);
		}
	}
}