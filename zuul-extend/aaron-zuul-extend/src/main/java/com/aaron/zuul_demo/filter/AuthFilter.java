package com.aaron.zuul_demo.filter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.aaron.zuul_demo.config.BasicConf;
import com.aaron.auth.common.ResponseCode;
import com.aaron.auth.common.ResponseData;
import com.aaron.auth.util.JWTUtils;
import com.aaron.auth.util.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 認證過濾器
 * @author aaron
 **/
public class AuthFilter extends ZuulFilter {

    // 注入 BasicConf 配置
	@Autowired
    private BasicConf basicConf;

    public AuthFilter() {
        super();
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    // 執行判斷邏輯
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = ctx.getRequest().getHeader("Authorization");
        JWTUtils jwtUtils = JWTUtils.getInstance();
        String apis = basicConf.getApiWhiteStr();
        
        //白名單，放過；將配置的白名單轉為 List，然後判斷當前請求的 URI 是否在白名單中，存在則放過
        List<String> whileApis = Arrays.asList(apis.split(","));
        String uri = ctx.getRequest().getRequestURI();
        if (whileApis.contains(uri)) {
        	System.err.println(uri + " 在白名單");
            return null;
        }
        
        // path uri 處理
        for (String wapi : whileApis) {
            if (wapi.contains("{") && wapi.contains("}")) {
                if (wapi.split("/").length == uri.split("/").length) {
                    String reg = wapi.replaceAll("\\{.*}", ".*{1,}");
                    System.err.println(reg);
                    Pattern r = Pattern.compile(reg);
                    Matcher m = r.matcher(uri);
                    if (m.find()) {
                        return null;
                    }
                }
            }
        }
        

        /**
         * 驗證 TOKEN
         * 路由之前的認證，攔截請求邏輯 
         * 當用戶登錄成功之後就能拿到 Token，在請求其他的接口時帶上 Token，就可以在 Zuul 的 Filter 中對 Token 進行認證
         * 攔截請求邏輯：
         * 1.從請求頭中獲得 Token，如果沒有就攔截並給出友善的提示。
         * 2.有 Token 則驗證 Token 合法性，合法在放行，不合法就攔截並給出友善提示。 
         */
        if (!StringUtils.hasText(token)) {
            ctx.setSendZuulResponse(false);
            // 設置 isSuccess=false 告知下面的 Filter 不需要執行
            ctx.set("isSuccess", false);
            ResponseData data = ResponseData.fail("非法請求【缺少Authorization信息】", ResponseCode.NO_AUTH_CODE.getCode());
            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }

        JWTUtils.JWTResult jwt = jwtUtils.checkToken(token);
        if (!jwt.isStatus()) {
            ctx.setSendZuulResponse(false);
            ctx.set("isSuccess", false);
            ResponseData data = ResponseData.fail(jwt.getMsg(), jwt.getCode());
            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        
        System.err.println("用戶ID"+jwt.getUid());
       
        /** 
         * 向下游微服務中傳遞認證以後的資訊
         * 傳統的單體項目都是使用 Session 來存儲登錄後的用戶資訊，如此將導致系統集群化後的用戶資訊都有存取上的問題。
         * 為了提高併發性能，方便快速擴容，服務都設計為無狀態，不須對每個服務都進行用戶是否登錄判斷
         * 微服務的解決方案：
         * 1.統一在 API Gateway 中認證即可
         * 2.API Gateway 認證之後用戶的資訊傳遞給下方的服務就是一個解決辦法
         */
        ctx.addZuulRequestHeader("uid", jwt.getUid());
        return null;
    }
}
