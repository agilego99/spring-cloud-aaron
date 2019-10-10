package org.gordianknot.conf.web.common;
import java.util.ArrayList;
import java.util.List;

import org.gordianknot.conf.web.filter.HTTPBasicAuthorizeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FilterConfig {
    
    /**
     * 註冊權限攔截Filter
     * @author aaron
     * @return
     */
    @Bean  
    public FilterRegistrationBean  filterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPBasicAuthorizeFilter httpBasicFilter = new HTTPBasicAuthorizeFilter();  
        registrationBean.setFilter(httpBasicFilter);  
        List<String> urlPatterns = new ArrayList<String>(1);  
        urlPatterns.add("/*");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }
    
}
