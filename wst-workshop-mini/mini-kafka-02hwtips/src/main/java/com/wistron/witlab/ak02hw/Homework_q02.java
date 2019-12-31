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
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.record.TimestampType;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * Tips:
 * 由於要驗證在一個有5個partitions的topic, 如果msgKey是"whereami"的訊息會被推送到那一個partitions。可以依以下步驟來實驗:
 *
 * Step1. 先用kakfa-topics的command來產生一個有5個partitons的topic:
 * bin/kafka-topics --create \
 *   --zookeeper localhost:2181 \
 *   --replication-factor 1 \
 *   --partitions 5 \
 *   --topic test5
 *
 * Step2. 再用Homework_q02_pub來推送一筆msgKey是"whereami"的訊息
 * Step3. 再用Homework_q02來訂閱這個topic, 然後看message的metadata就得到了解答
 */
public class Homework_q02 {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String STUDENT_ID = "STUDENTID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("group.id", STUDENT_ID); // <-- 這就是ConsumerGroup
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgKey的反序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgValue的反序列化器
        props.put("auto.offset.reset", "earliest"); // 是否從這個ConsumerGroup尚未讀取的partition/offset開始讀
        // 步驟2. 產生一個Kafka的Consumer的實例
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        // 步驟3. 指定想要訂閱訊息的topic名稱
        String topicName = "test5";
        // 步驟4. 讓Consumer向Kafka集群訂閱指定的topic
        consumer.subscribe(Arrays.asList(topicName), new SeekToListener(consumer));
        // 步驟5. 持續的拉取Kafka有進來的訊息
        try {
            System.out.println("Start listen incoming messages ...");
            while (true) {
                // 請求Kafka把新的訊息吐出來
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                // 如果有任何新的訊息就會進到下面的迭代
                for (ConsumerRecord<String, String> record : records){
                    // ** 在這裡進行商業邏輯與訊息處理 **
                    // 取出相關的metadata
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();
                    TimestampType timestampType = record.timestampType();
                    long timestamp = record.timestamp();
                    // 取出msgKey與msgValue
                    String msgKey = record.key();
                    String msgValue = record.value();
                    // 秀出metadata與msgKey & msgValue訊息

                    // ** “題目#2” 的就是看partition的id **
                    if(msgKey.equalsIgnoreCase("whereami")) {
                        System.out.println(topic + "-" + partition + "-" + offset + " : (" + record.key() + ", " + record.value() + ")");
                        System.out.println("The partition is " + partition);
                    }
                }
            }
        } finally {
            // 步驟6. 如果收到結束程式的訊號時關掉Consumer實例的連線
            consumer.close();
            System.out.println("Stop listen incoming messages");
        }
    }
}
