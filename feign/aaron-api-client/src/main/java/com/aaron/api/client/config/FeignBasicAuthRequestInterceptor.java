package com.aaron.api.client.config;
import org.gordianknot.common.filter.HttpHeaderParamFilter;
import org.gordianknot.common.support.RibbonFilterContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Map;
/**
 * Feign 請求攔截器
 * @author aaron
 **/
public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {
    public FeignBasicAuthRequestInterceptor() {
    }
    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", System.getProperty("aaron.auth.token"));
        Map<String, String> attributes = RibbonFilterContextHolder.getCurrentContext().getAttributes();
        for (String key :  attributes.keySet()) {
            String value = attributes.get(key);
            System.out.println("feign :" + key + "\t" + value);
            template.header(key, value);
        }
    }
}
