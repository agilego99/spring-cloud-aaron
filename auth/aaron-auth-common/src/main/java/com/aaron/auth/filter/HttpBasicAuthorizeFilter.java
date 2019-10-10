package com.aaron.auth.filter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import com.aaron.auth.common.ResponseCode;
import com.aaron.auth.common.ResponseData;
import com.aaron.auth.util.JWTUtils;
import com.aaron.auth.util.JsonUtils;
/**
 * 認證過濾器；下述的 Filter 類中對所有請求進行攔截，其調用之前的 JwtUtils 來檢查 Token 是否合法，合法則放行，不合法則攔截，並回應適當的資訊。
 * API 調用權限控制
 * @author aaron
 *
 */
public class HttpBasicAuthorizeFilter implements Filter {
 	JWTUtils jwtUtils = JWTUtils.getInstance();
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
        String auth = httpRequest.getHeader("Authorization");
        //驗證TOKEN
 		if (!StringUtils.hasText(auth)) {
 			PrintWriter print = httpResponse.getWriter();
 			print.write(JsonUtils.toJson(ResponseData.fail("非法請求【缺少Authorization信息】", ResponseCode.NO_AUTH_CODE.getCode())));
 			return;
 		}
 		JWTUtils.JWTResult jwt = jwtUtils.checkToken(auth);
 		if (!jwt.isStatus()) {
 			PrintWriter print = httpResponse.getWriter();
 			print.write(JsonUtils.toJson(ResponseData.fail(jwt.getMsg(), jwt.getCode())));
 			return;
 		}
 		chain.doFilter(httpRequest, response);
 	}
 	
 	@Override
 	public void destroy() {
 		
 	}
}
