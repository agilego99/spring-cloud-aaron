package com.aaron.ribbon_eureka_demo.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 為某個客戶端指定配置
 * @author aaron
 *
 */
@RibbonClient(name = "aaron-ribbon-config-demo", configuration = BeanConfiguration.class) 
public class RibbonClientConfig {
}
