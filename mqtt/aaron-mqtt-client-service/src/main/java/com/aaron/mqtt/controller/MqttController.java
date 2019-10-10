package com.aaron.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.mqtt.config.MqttMessageGateway;

@RestController
@RequestMapping("/mqtt")
public class MqttController {
 
    @Autowired
    private MqttMessageGateway mqttMessageGateway;
    
    @RequestMapping(value="/post", method=RequestMethod.POST) 
    @ResponseBody
    public String postCarGPS(String message,String topicName) {
    	
        mqttMessageGateway.sendToMqtt(message,"vehicle/report/test");
        return message;
    }
}
