/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wistron.witlab.ak02hw;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

/**
 * 一個用來重設某一個ConsumerGroup的offset值到一個Topic/Partition的最小值。方便測試與教學
 */
public class SeekToListener implements ConsumerRebalanceListener {
    boolean firstTime = true;
    Consumer consmer;

    public SeekToListener(Consumer consmer){
        this.consmer = consmer;
    }


    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        // 監聽Kafka的Consumer Rebalance的event, 如果是程式跑起來的第一次
        // 我們重新對每一個partition的offset來進行重置到現在partition最小的offset值
        if(firstTime){
            consmer.seekToBeginning(partitions);
            System.out.println("SeekToBeginning of " + partitions);
        }
    }
}
