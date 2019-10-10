package com.aaron.fsh.house.config;
import org.gordianknot.conf.client.init.ConfInit;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.gordianknot.common.aspect.ApiLimitAspect;
import com.aaron.fsh.house.listener.InitGatewayApiLimitRateListener;
import org.gordianknot.jdbc.GordianknotTemplate;
/**
 * 配置信息
 *
 * @author aaron
 **/

@Configuration
public class BeanConfig {
    /**
     * JDBC
     * @return
     */
    @Bean(autowire= Autowire.BY_NAME)
    public GordianknotTemplate gordianknotTemplate() {
        return new GordianknotTemplate("com.aaron.fsh.house.po");
    }
    /**
     * 具體的API併發控制
     * @return
     */
    @Bean
    public ApiLimitAspect apiLimitAspect() {
        return new ApiLimitAspect();
    }
    @Bean
    public InitGatewayApiLimitRateListener initGatewayApiLimitRateListener() {
 		return new InitGatewayApiLimitRateListener("com.aaron.fsh.house.controller");
 	}
   
    @Bean
    public ConfInit confInit() {
    	return new ConfInit();
    }
}
