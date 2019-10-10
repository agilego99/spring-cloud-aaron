package com.aaron.mqtt.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiveMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		String str = "";
		try {
			 str = (String) message.getPayload();
			 
			 log.info("Received message from MQTT\t" + str);
			 
		} catch (Exception e) {
			log.error("Exception" + e.getMessage());
			log.error("Received message from MQTT\t" + str);
		}
	}
}