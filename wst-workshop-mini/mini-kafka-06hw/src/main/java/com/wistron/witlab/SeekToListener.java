package com.wistron.witlab;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

public class SeekToListener implements ConsumerRebalanceListener {
    boolean isFirstTime = true;
    Consumer consumer;

    public SeekToListener(Consumer consmer){
        this.consumer = consmer;
    }


    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

    }

    // 由於PartitionsAssigned會在Consumer執行的過程中當有Consumer加入ConsumerGroup或從ConsumerGroup被移除時
    // 就會觸發onPartitionsAssigned的事件, 因此在程式中去檢查是否為第一次的事件被觸發。避免程式一直重覆地reset offset
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        if(isFirstTime) {
            consumer.seekToBeginning(partitions);
            isFirstTime = false;
        }
    }
}
