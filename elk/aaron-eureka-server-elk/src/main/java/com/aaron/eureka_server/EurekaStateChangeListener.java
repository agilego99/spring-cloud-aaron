package com.aaron.eureka_server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.aaron.eureka_server.utils.LogStashUtil;
import com.netflix.appinfo.InstanceInfo;

@Component
public class EurekaStateChangeListener {
	
	
	// elk_logger 定義只須上傳的 log 
	Logger logstashName1 = LoggerFactory.getLogger(LogStashUtil.LOGSTASH_NAME1);
	Logger logstashName2 = LoggerFactory.getLogger(LogStashUtil.LOGSTASH_NAME2);
	
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服務下線");
    }
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.err.println(instanceInfo.getAppName() + "進行註冊");
    }
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服務進行續約");
    }
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.err.println("註冊中心 啓動");
    	elkLogger1.info(LogStashUtil.concat("elk 測試","進行註冊"));
        elkLogger2.info(LogStashUtil.concat("elk 測試","進行註冊"));

    }
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.err.println("Eureka Server 啓動");
    }
}