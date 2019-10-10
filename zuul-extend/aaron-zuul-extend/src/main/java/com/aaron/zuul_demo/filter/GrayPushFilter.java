package com.aaron.zuul_demo.filter;
import org.springframework.beans.factory.annotation.Autowired;
import com.aaron.zuul_demo.config.BasicConf;
import com.aaron.zuul_demo.support.RibbonFilterContextHolder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
 * 灰度發布過濾器
 * @author aaron
 * @create 2019/8/26
 **/
public class GrayPushFilter extends ZuulFilter {
    @Autowired
    private BasicConf basicConf;
    public GrayPushFilter() {
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
        return "route";
    }
    @Override
    public int filterOrder() {
        return 6;
    }
   
    /**
     * 主要邏輯：
     * 基於附載均衡策略，在其中加入灰度發布邏輯，在此基於 RoundBobinRule 規則來進行改造。
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // AuthFilter驗證成功之後設置的用戶編號
        String loginUserId = ctx.getZuulRequestHeaders().get("uid");
        RibbonFilterContextHolder.getCurrentContext().add("userId", loginUserId);
        // 灰度發佈的服務資訊
        RibbonFilterContextHolder.getCurrentContext().add("servers",basicConf.getGrayPushServers());
        // 灰度發佈的用戶ID資訊
        RibbonFilterContextHolder.getCurrentContext().add("userIds", basicConf.getGrayPushUsers());
        return null;
    }
}
