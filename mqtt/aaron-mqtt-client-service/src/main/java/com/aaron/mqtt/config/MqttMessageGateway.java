package com.aaron.mqtt.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;


/**
 * 消息發送接口，不需要實現，spring 會通過代理的方式實現
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttMessageGateway {
    void sendToMqtt(String data,@Header(MqttHeaders.TOPIC) String topic);
}
