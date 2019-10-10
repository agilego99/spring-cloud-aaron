package com.aaron.fsh.substitution.config;
import org.gordianknot.common.filter.HttpBasicAuthorizeFilter;
import org.gordianknot.common.filter.HttpHeaderParamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;
/**
 * 過濾器配置
 * @author aaron
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HttpHeaderParamFilter httpHeaderParamFilter = new HttpHeaderParamFilter();
        registrationBean.setFilter(httpHeaderParamFilter);
        List<String> urlPatterns = new ArrayList<String>(1);
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
