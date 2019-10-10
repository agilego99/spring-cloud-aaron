package com.aaron.fsh.substitution.config;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.aaron.fsh.substitution.mq.HouseProcessor;

/**
 * Stream 配置
 * @author aaron
 **/
@Configuration
@EnableBinding(HouseProcessor.class)
public class IntegrationConfig {

}
