package com.aaron.fsh.user.conf;

import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;

/**
 * Eureka配置信息 (多個系统共用)
 * @author aaron
 **/
@GordianknotConf(system = "gordianknot-common", env = true, prefix = "eureka")
public class EurekaConf {
    @ConfField("Eureka注册中心地址")
//    private String defaultZone = "http://aaron:999999@master:8761/eureka/";
    private String defaultZone = "http://aaron:999999@localhost:8761/eureka/";

    public String getDefaultZone() {
        return defaultZone;
    }

    public void setDefaultZone(String defaultZone) {
        this.defaultZone = defaultZone;
    }
}
