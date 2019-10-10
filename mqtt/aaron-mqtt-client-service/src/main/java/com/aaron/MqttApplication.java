package com.aaron;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@ComponentScan(basePackages = "com.aaron")
@Slf4j
public class MqttApplication {
	
    public static void main(String[] args) throws MqttException, InterruptedException{
    	
    	log.info("Spring start.");
    	ApplicationContext ctx = SpringApplication.run(MqttApplication.class,args);
        log.info("System boot is completed.");
        
    }
}