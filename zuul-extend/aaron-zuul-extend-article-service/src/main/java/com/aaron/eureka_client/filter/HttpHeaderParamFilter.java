package com.aaron.eureka_client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.eureka_client.support.RibbonFilterContextHolder;
/**
 * 接收 Zuul 過來的用戶資訊
 * @author aaron
 * 內部服務間的用戶資訊傳遞（除了接口以外的另一種方式）
 * 當 A 服務調用 B 服務時，就需要透過框架層面將用戶的資訊傳遞到 B 服務當中
 * 以上機制可透過以下的封裝的方式來實現
 */
public class HttpHeaderParamFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json; charset=utf-8");
		String uid = httpRequest.getHeader("uid");
		RibbonFilterContextHolder.getCurrentContext().add("uid", uid);
		System.err.println("Filter已經截獲到用戶的請求地址\t" + httpRequest.getServletPath());
		System.err.println("來自 aaron-zuul-extend 寫入 Header 的 uid\t" + uid);
		// Filter只是鏈式處理，請求依然放行到目的地址
		 chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {
	}
}