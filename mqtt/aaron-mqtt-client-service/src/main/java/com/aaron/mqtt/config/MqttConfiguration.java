package com.aaron.mqtt.config;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@IntegrationComponentScan
@Data
@Slf4j
public class MqttConfiguration {

	@Value("${mqtt.broker.url}")
	private String brokerUrl;

	/*
	 * auth
	 */
	@Value("${mqtt.broker.security.hasauth}")
	private boolean hasAuth;
	
	@Value("${mqtt.broker.security.username}")
	private String username;

	@Value("${mqtt.broker.security.password}")
	private String password;
	
	/*
	 * tls
	 */
	@Value("${mqtt.broker.security.hastls}")
	private boolean hasTLS;
	
	@Value("${mqtt.broker.security.ca.path}")
	private String ca;

	@Value("${mqtt.broker.security.cert.path}")
	private String cert;

	@Value("${mqtt.broker.security.key.path}")
	private String key;

	@Value("${mqtt.broker.keep.alive}")
	private int keepAlive;
	
	@Value("${mqtt.broker.qos}")
	private int qos;

	@Value("${mqtt.broker.connect.timeout}")
	private int connectTimeout;
	
	private Random rand = new Random();
	private String cliendId;
	
    
	@Value("${mqtt.broker.scriber.topic.gbus}")
	private String gbusSubTopic;
    
	@Value("${mqtt.broker.scriber.topic.mkz}")
	private String mkzSubTopic;
	
	@Value("${mqtt.broker.scriber.topic.itri}")
	private String itriSubTopic;
	
	@Value("${mqtt.broker.scriber.topic.cht}")
	private String chtSubTopic;
	
	@Value("${mqtt.broker.scriber.topic.test}")
	private String testSubTopic;
	
	
	// ==================== 連接 mqtt broker ====================
	@Bean
	public MqttPahoClientFactory clientFactory() {
		DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
		clientFactory.setServerURIs(brokerUrl);

		if (hasAuth) {
			try {
				clientFactory.setUserName(username); 
				clientFactory.setPassword(password); 
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		clientFactory.setConnectionTimeout(connectTimeout);
		clientFactory.setKeepAliveInterval(keepAlive);
		// TLS連接配置
		if (hasTLS) {
			try {
				clientFactory.setSocketFactory(SslUtil.getSocketFactory(ca, cert, key, ""));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return clientFactory;
	}

	// ==================== 生產者 ====================
	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel") // 綁定生產者
	public MqttPahoMessageHandler mqttOutbound(MqttPahoClientFactory clientFactory) {
		
		cliendId = RandomStringUtils.randomAlphabetic(15);
		
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(cliendId, clientFactory);
		messageHandler.setAsync(true);
		messageHandler.setDefaultQos(qos); // AWS 必須設為 0，才可以收到！
		messageHandler.setDefaultRetained(false);
		messageHandler.setAsyncEvents(false);
		return messageHandler;
	}

	// ==================== 消費者 ====================
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel") // 綁定消費者
	public MessageHandler handler() {
		return new ReceiveMessageHandler();
	}

	// 訂閱在此
	@Bean
	public MessageProducerSupport mqttInbound() {
		
		cliendId = RandomStringUtils.randomAlphabetic(15);
		
		/**
		 * gbus
		 * mkz
		 * cht(kingWay)
		 * itri
		 * 
		 */
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(cliendId , clientFactory(),
				gbusSubTopic,mkzSubTopic,chtSubTopic,itriSubTopic,testSubTopic); // 可在此訂閱多個 topic
//		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(cliendId , clientFactory(),
//				testSubTopic); // 可在此訂閱多個 topic
		
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(qos);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}
}