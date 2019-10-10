package com.aaron.mqtt.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aaron.mqtt.config.MqttMessageGateway;

import lombok.Data;

/*
 * @Service用於標注業務層組件
 * @Controller用於標注控制層組件（如struts中的action）
 * @Repository用於標注數據訪問組件，即DAO組件
 * @Component泛指組件，當組件不好歸類的時候，我們可以使用這個註解進行標注。
 */

@Component
@Data
public class MqttMessageComponentGateway {

	@Autowired MqttMessageGateway mqttMessageGateway;
	
	/**
	 * MQTT Geateway
	 */
	public void sendToMqtt(String message,String topic) {
		
		mqttMessageGateway.sendToMqtt(message, topic);	
	}
	
}
