package com.aaron.fsh.house.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.aaron.fsh.house.mq.HouseProcessor;

/**
 * Stream 配置
 * @author aaron
 **/
@Configuration
@EnableBinding(HouseProcessor.class)
public class IntegrationConfig {

}
