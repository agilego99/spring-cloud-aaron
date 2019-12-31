package com.aaron.eureka_server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.aaron.eureka_server.utils.LogUtil;
import com.netflix.appinfo.InstanceInfo;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class EurekaStateChangeListener {
	
	// elk_logger 定義只須上傳的 log 
	Logger elkLogger = LoggerFactory.getLogger("elk_logger");
	
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服務下線");
    }
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        elkLogger.info(LogUtil.concat(instanceInfo.getAppName(),"進行註冊"));
        System.err.println(instanceInfo.getAppName() + "進行註冊");
    }
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服務進行續約");
    }
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
    	 elkLogger.info(LogUtil.concat("註冊","啓註冊"));
        System.err.println("註冊中心 啓動");
    }
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
    	elkLogger.info(LogUtil.concat("Eureka Server 啓動"));
        System.err.println("Eureka Server 啓動");
    }
}