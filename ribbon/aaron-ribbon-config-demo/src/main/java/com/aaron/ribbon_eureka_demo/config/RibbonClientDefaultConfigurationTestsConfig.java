package com.aaron.ribbon_eureka_demo.config;

import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * 默認配置
 * @author aaron
 *
 */
@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
public class RibbonClientDefaultConfigurationTestsConfig {

}