package com.aaron.eureka_client.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import brave.Span;
import brave.Tracer;
/**
 * 負責處理請求集回應的模組
 * 透過註冊自定義的 TracingFilter 實例來實現擴展性的需求
 * 本例為添加自定義的「標記」以及將「請求 ID」添加到回應頭返回給客戶端
 * @author Aaron
 *
 */
@Component
@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
class MyFilter extends GenericFilterBean {

	private final Tracer tracer;

	MyFilter(Tracer tracer) {
		this.tracer = tracer;
	}

	@Override 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Span currentSpan = this.tracer.currentSpan();
		if (currentSpan == null) {
			chain.doFilter(request, response);
			return;
		}
		// 請求 ID
		((HttpServletResponse) response).addHeader("ZIPKIN-TRACE-ID",
						currentSpan.context().traceIdString());
		// 標記
		currentSpan.tag("custom", "tag");
		chain.doFilter(request, response);
	}
}
