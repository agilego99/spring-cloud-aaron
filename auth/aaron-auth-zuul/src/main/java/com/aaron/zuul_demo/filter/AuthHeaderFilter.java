package com.aaron.zuul_demo.filter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
 * 調用服務前添加認證請求頭過濾器
 * Zuul 中傳遞 Token 到路由的服務中
 *
 * @author aaron
 *
 **/
public class AuthHeaderFilter extends ZuulFilter {
 	public AuthHeaderFilter() {
 		super();
 	}
 	@Override
 	public boolean shouldFilter() {
 		RequestContext ctx = RequestContext.getCurrentContext();
 		Object success = ctx.get("isSuccess");
 		return success == null ? true : Boolean.parseBoolean(success.toString());
 	}
 	@Override
 	public String filterType() {
 		return "pre";
 	}
 	@Override
 	public int filterOrder() {
 		return 1;
 	}
 	@Override
 	public Object run() {
 		RequestContext ctx = RequestContext.getCurrentContext();
 		System.err.println("Zull 請求過濾器");
 		ctx.addZuulRequestHeader("Authorization", System.getProperty("fangjia.auth.token"));
 		return null;
 	}
}
