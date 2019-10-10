package com.aaron.fsh.substitution.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;
import org.springframework.web.client.RestTemplate;

import com.aaron.fsh.substitution.rule.MyLoadBalanced;
import com.aaron.fsh.substitution.rule.MyRule;

@Configuration
public class BeanConfiguration {
	@Bean  
    Logger.Level feignLoggerLevel() {  
        return Logger.Level.FULL;  
    }

    @Bean
    //@LoadBalanced
    @MyLoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public MyRule rule() {
	    return new MyRule();
    }*/
}
