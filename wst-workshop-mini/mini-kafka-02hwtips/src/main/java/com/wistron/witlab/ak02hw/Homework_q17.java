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
import java.util.*;

/**
 * Tips:
 * 由於17題主要是找出兩個topics裡頭各自的unique的商品貨物編號,我們可以利用“Set"的資料容器物件來快速達成目的。(概念同13題)
 * 但這個範例示範了如何同時訂閱多個kafka topics。
 */
public class Homework_q17 {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String STUDENT_ID = "STUDENTID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("group.id", "{YOUR_STUDENT_ID}"); // <-- 這就是ConsumerGroup
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgKey的反序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgValue的反序列化器
        props.put("auto.offset.reset", "earliest"); // 是否從這個ConsumerGroup尚未讀取的partition/offset開始讀
        // 步驟2. 產生一個Kafka的Consumer的實例
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        // 步驟3. 指定想要訂閱訊息的topic名稱
        String topic_balancelog  = "ak02.hw.balancelog";
        String topic_balancelog2 = "ak02.hw.balancelog2";

        // 步驟4. 讓Consumer向Kafka集群訂閱指定的topics (一次訂閱多個topics)
        consumer.subscribe(Arrays.asList(topic_balancelog, topic_balancelog2), new SeekToListener(consumer)); // ** Tips: 讓這支程式每次重起時都把offset移到最前面的SeekToListener

        // 步驟5. 持續的拉取Kafka有進來的訊息
        int record_count = 0;

        // 產生兩個Set來保存unique的msgKey <---- 存放 “題目#17” 答案的容器
        Set<String> balancelog_parts_unique = new HashSet<>();
        Set<String> balancelog2_parts_unique = new HashSet<>();
        try {
            System.out.println("Start listen incoming messages ...");
            // 全局計時開始
            long gtime_start = System.nanoTime();
            while (true) {
                // 計時開始
                long time_start = System.nanoTime();
                // 請求Kafka把新的訊息吐出來
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                // 如果有任何新的訊息就會進到下面的迭代
                for (ConsumerRecord<String, String> record : records){
                    record_count++;
                    // ** 在這裡進行商業邏輯與訊息處理 **
                    // 取出相關的metadata
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();
                    TimestampType timestampType = record.timestampType();
                    long timestamp = record.timestamp();
                    // 取出msgKey與msgValue
                    String msgKey = record.key(); // << 這個是商品貨物編號
                    String msgValue = record.value(); // << 這個是商品貨物交易與結餘(balance)資料

                    // ** “題目#17” 的主要邏輯 **

                    // *** 你要開發的Block在這裡 [Start] ***

                    // Tips: 主要的做法同題目#13, 只不過這一題裡有2個SET, 因此要用Topic名稱來判斷看資料要放到那個SET
                    // Tips: 在Java中把值放進"Set"的collection, 使用的method是 set_object.add(商品貨物編號)

                    // ...
                    // ...

                    // *** 你要開發的Block在這裡 [End] ***
                }

                // 計時結束
                long time_spend = System.nanoTime() - time_start;
                // 打印累積讀取筆數與每次迭代所花費的處理時間
                System.out.println("");
                System.out.println("Accm. Record count: " + record_count + ", Accm. Time spends:" + (System.nanoTime()-gtime_start) + " nano seconds, Time spends: " + time_spend + " nano seconds");
                // 打印出最更新的結果 <---- “題目#17” 的答案
                System.out.println("Parts of balancelog : " + sortingSet(balancelog_parts_unique) + ", count: " + balancelog_parts_unique.size());
                System.out.println("Parts of balancelog2: " + sortingSet(balancelog2_parts_unique) + ", count: " + balancelog2_parts_unique.size());

                // ** 觀察產生的"Accm. Record count"的數值, 如果沒有持續增加就代表
                //    程式己經把現在topic裡有的訊息全部都收進來並且你看到的結果就是問題的答案 **
            }
        } finally {
            // 步驟6. 如果收到結束程式的訊號時關掉Consumer實例的連線
            consumer.close();
            System.out.println("Stop listen incoming messages");
        }
    }

    // 一個簡單用來對Set容器進行排序的函式
    private static List<String> sortingSet(Set<String> datas) {
        List<String> sorted_result = new ArrayList<>(datas);
        Collections.sort(sorted_result);
        return sorted_result;
    }
}
