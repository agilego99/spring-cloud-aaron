package com.aaron.fsh.substitution.mq;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
/**
 * 房產消費類
 * @author aaron
 **/
@Component
public class HouseConsumer {
    @StreamListener(HouseProcessor.ADD_HOUSE_INPUT)
    public void addHouseInput(Message<String> message) {
        System.out.println("新增房產監聽收到：" + message.getPayload());
    }
}
