package com.aaron.zuul_demo.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class DebugRequestFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
	}

	// 數字越小，優先級越高
	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
    // 當 return 為 false 時，則此過濾器不再執行。
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest req = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
		System.err.println("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
		StringBuilder params = new StringBuilder("?");
		// 獲取URL參數
		Enumeration<String> names = req.getParameterNames();
		if (req.getMethod().equals("GET")) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				params.append(name);
				params.append("=");
				params.append(req.getParameter(name));
				params.append("&");
			}
		}
		if (params.length() > 0) {
			params.delete(params.length() - 1, params.length());
		}
		System.err.println(
				"REQUEST:: > " + req.getMethod() + " " + req.getRequestURI() + params + " " + req.getProtocol());
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
			String name = (String) headers.nextElement();
			String value = req.getHeader(name);
			System.err.println("REQUEST:: > " + name + ":" + value);
		}
		final RequestContext ctx = RequestContext.getCurrentContext();
		// 獲取請求體參數
		if (!ctx.isChunkedRequestBody()) {
			ServletInputStream inp = null;
			try {
				inp = ctx.getRequest().getInputStream();
				String body = null;
				if (inp != null) {
					body = IOUtils.toString(inp);
					System.err.println("REQUEST:: > " + body);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<Pair<String, String>> headerList = RequestContext.getCurrentContext().getOriginResponseHeaders();
		for (Pair<String, String> pair : headerList) {
			System.err.println("RESPONSE HEADER:: > " + pair.second());
		}
		// 第一種，獲取響應結果
		/*
		 * try { Object zuulResponse =
		 * RequestContext.getCurrentContext().get("zuulResponse"); if (zuulResponse !=
		 * null) { RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse; String
		 * body = IOUtils.toString(resp.getBody()); System.err.println("RESPONSE:: > " +
		 * body); resp.close();
		 * RequestContext.getCurrentContext().setResponseBody(body); } } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		// 第二種，獲取響應結果
		InputStream stream = RequestContext.getCurrentContext().getResponseDataStream();
		try {
			if (stream != null) {
				String body = IOUtils.toString(stream);
				System.err.println("RESPONSE:: > " + body);
				RequestContext.getCurrentContext().setResponseBody(body);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}