package com.aaron.zuul_demo.filter;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import com.aaron.zuul_demo.config.BasicConf;
import com.aaron.auth.common.ResponseCode;
import com.aaron.auth.common.ResponseData;
import com.aaron.auth.util.JsonUtils;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
 * 限流過濾器
 *
 * @author aaron
 * @create 2019-08-26
 **/
public class LimitFilter extends ZuulFilter {
    private Logger log = LoggerFactory.getLogger(LimitFilter.class);
    public static volatile RateLimiter rateLimiter = RateLimiter.create(100.0);
   
    @Autowired
    @Qualifier("longRedisTemplate")
    private RedisTemplate<String, Long> redisTemplate;
    @Autowired
    private BasicConf basicConf;
   
    public LimitFilter() {
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
        return 0;
    }
    // 集群限流，透過 Redis 管理
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Long currentSecond = System.currentTimeMillis() / 1000;
        // 轉換為時間戳（秒）
        // 可以搭配 Zuul 提供的 Router Filter 來做，在 Router Filter 中可以直接獲取當前請求是要轉發到哪個服務。
        String key = "fsh-api-rate-limit-" + currentSecond;
        try {
            if (!redisTemplate.hasKey(key)) {
                redisTemplate.opsForValue().set(key, 0L, 100, TimeUnit.SECONDS);
            }
            // 當集群中當前秒的併發量達到了設定的值，不進行處理，注意集群中的網關所在服務器時間必須同步
            if (redisTemplate.opsForValue().increment(key, 1) > basicConf.getClusterLimitRate()) {
                ctx.setSendZuulResponse(false);
                ctx.set("isSuccess", false);
                ResponseData data = ResponseData.fail("當前負載太高，請稍後重試", ResponseCode.LIMIT_ERROR_CODE.getCode());
                System.err.print("當前負載太高，請稍後重試\t" + ResponseCode.LIMIT_ERROR_CODE.getCode());
                ctx.setResponseBody(JsonUtils.toJson(data));
                ctx.getResponse().setContentType("application/json; charset=utf-8");
                return null;
            }
        } catch (Exception e) {
           log.error("集群限流異常", e);
            /**
             *  Redis 掛掉等異常處理，可以繼續單節點限流
             *  啟用單節點限流
             */
            rateLimiter.acquire();
        }
        return null;
    }
    
    // 總體限流；單節點限流
//    @Override
//    public Object run(){ 
//    	rateLimiter.acquire();
//    	return null;
//    }
}
