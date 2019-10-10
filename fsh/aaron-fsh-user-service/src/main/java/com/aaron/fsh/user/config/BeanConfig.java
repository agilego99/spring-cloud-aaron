package com.aaron.fsh.user.config;

import org.gordianknot.jdbc.GordianknotTemplate;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置信息
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
        return new GordianknotTemplate("com.aaron.fsh.user.po");
    }

}
