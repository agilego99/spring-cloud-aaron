package com.aaron.zuul_demo.filter;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aaron.zuul_demo.config.BasicConf;
import com.aaron.auth.common.ResponseCode;
import com.aaron.auth.common.ResponseData;
import com.aaron.auth.util.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 服務降級過濾器
 *
 * @author aaron
 */
public class DownGradeFilter extends ZuulFilter {

    @Autowired
    private BasicConf basicConf;

    /**
     * Instantiates a new Down grade filter.
     */
    public DownGradeFilter() {
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
        return 4;
    }

    /**
     * 主要邏輯：
     * 1.通過 RequestContext 獲取即將路由的服務 ID，透過配置資訊獲取降級的服務資訊。
     * 2.如果當前路由的服務在其中，就直接拒絕，返回對應得資訊讓客戶端做對應的處理。
     * 3.當需要降級的時候，直接在 Apollo 配置中心修改配置就可以馬上生效。
     * 4.當然配置也可改為自動化，比如監控某些指標，如流量、負載，當達到某一定的指標後就自動觸發降級。
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Object serviceId = ctx.get("serviceId");
        if (serviceId != null && basicConf != null) {
            List<String> serviceIds = Arrays.asList(basicConf.getDownGradeServiceStr().split(","));
            if (serviceIds.contains(serviceId.toString())) {
                ctx.setSendZuulResponse(false);
                ctx.set("isSuccess", false);
                System.err.println("降級的服務\t"+serviceId.toString());
                ResponseData data = ResponseData.fail("服務降級中", ResponseCode.DOWNGRADE.getCode());
                ctx.setResponseBody(JsonUtils.toJson(data));
                ctx.getResponse().setContentType("application/json; charset=utf-8");
                return null;
            }
        }
        return null;
    }
}
