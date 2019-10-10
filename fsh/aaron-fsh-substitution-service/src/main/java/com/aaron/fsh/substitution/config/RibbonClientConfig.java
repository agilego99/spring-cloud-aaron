package com.aaron.fsh.substitution.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author aaron
 **/
@RibbonClient(name = "fsh-house", configuration = BeanConfiguration.class)
public class RibbonClientConfig {

}
