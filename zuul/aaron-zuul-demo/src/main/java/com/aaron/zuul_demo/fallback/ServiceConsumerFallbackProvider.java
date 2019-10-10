package com.aaron.zuul_demo.fallback;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.aaron.zuul_demo.base.ResponseCode;
import com.aaron.zuul_demo.base.ResponseData;
import com.aaron.zuul_demo.util.JsonUtils;

@Component
public class ServiceConsumerFallbackProvider implements FallbackProvider {
	
	private Logger log = LoggerFactory.getLogger(ServiceConsumerFallbackProvider.class);

	
	@Override
	public String getRoute() {
	    // 返回 * 表示對所有服務進行回退操作，如果只想對某個服務進行回退，則需要在返回上填寫回退的服務名稱（此名稱為 Eureka 的名稱）
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			
			// 返回響應的狀態碼
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			// 返回響應狀態碼對應的內容資料
			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				if (cause != null) {
					log.error("", cause.getCause());
				}
				ResponseData data = ResponseData.fail(route+"服務內部錯誤", ResponseCode.SERVER_ERROR_CODE.getCode());
				return new ByteArrayInputStream(JsonUtils.toJson(data).getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}
		};
	}
}
