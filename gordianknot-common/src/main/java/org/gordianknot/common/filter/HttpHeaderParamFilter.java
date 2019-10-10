package org.gordianknot.common.filter;

import org.gordianknot.common.base.ResponseCode;
import org.gordianknot.common.base.ResponseData;
import org.gordianknot.common.support.RibbonFilterContextHolder;
import org.gordianknot.common.util.JWTUtils;
import org.gordianknot.common.util.JsonUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 參數傳遞過濾器
 * @author aaron
 *
 */
public class HttpHeaderParamFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8"); 
        String uid = httpRequest.getHeader("uid");
		//RibbonFilterContextHolder.clearCurrentContext();
		RibbonFilterContextHolder.getCurrentContext().add("uid", uid);
		chain.doFilter(httpRequest, response);
	}
	
	@Override
	public void destroy() {
		
	}

}
