package com.aaron.fsh.house.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.gordianknot.common.filter.HttpBasicAuthorizeFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 過濾器配置
 * @author aaron
 **/
@Configuration
public class FilterConfig {
//    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HttpBasicAuthorizeFilter httpBasicFilter = new HttpBasicAuthorizeFilter();
        registrationBean.setFilter(httpBasicFilter);
        List<String> urlPatterns = new ArrayList<String>(1);
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
