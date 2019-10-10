package com.aaron.zuul_demo.filter;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.aaron.zuul_demo.base.ResponseCode;
import com.aaron.zuul_demo.base.ResponseData;
import com.aaron.zuul_demo.util.IpUtils;
import com.aaron.zuul_demo.util.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
public class IpFilter extends ZuulFilter {
    // IP黑名單列表
    private List<String> blackIpList = Arrays.asList("127.0.0.1");
    public IpFilter() {
        super();
    }
    
    // 是否執行過濾器，true 為執行，false 為不執行；可以利用配置中心來實現，達到動態的開啟和關閉過濾器
    @Override     
    public boolean shouldFilter() {
        return true;
    }
    
    // 過濾器類型，可選 pre、route、post、error
    @Override
    public String filterType() {
        return "pre";
    }
    
    // 過濾器執行優先順序；優先級數值越小，優先級越高
    @Override
    public int filterOrder() {
        return 1;
    }
    
    // 業務邏輯
    @Override
    public Object run() {
        
    	// 模擬 java.lang.ArithmeticException: by zero exception
//    	System.err.println(2/0);
        
    	RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtils.getIpAddr(ctx.getRequest());
        
        // 在黑名單中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
        	// forward:/local 的路由，ctx.setSendZuulResponse(false) 對 forward 無產生作用 
        	ctx.setSendZuulResponse(false);
            /* 用來攔截本地轉發請求，當配置了 當 ctx.setSendZuulResponse(false) 對 forward:/local 是不起作用，
             * 因此需設定 ctx.set("sendForwardFilter.ran", true) 才行；
             * 透過 getRequest 的 set 賦予 Key/Value 
             */
            ctx.set("sendForwardFilter.ran", true); 
            ResponseData data = ResponseData.fail("非法請求", ResponseCode.NO_AUTH_CODE.getCode());
            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}