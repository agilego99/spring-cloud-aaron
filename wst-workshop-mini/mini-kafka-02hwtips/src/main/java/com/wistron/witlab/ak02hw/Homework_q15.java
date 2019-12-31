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
 * 由於15題主要是By每一個unique的商品貨物編號算出它的庫存異動數,在資料處理上, 我們先對msgValue進行解析。
 * 然後可以利用一個“Map(key是part_no, value是庫存異動數)"來保存庫存值加減之後的結果。
 *
 * 公式: 期初庫存 + 庫存異動數 = 期末庫存
 *
 * 只要知道公式中的其中兩個值就可以知道剩下的那個值了。
 */
public class Homework_q15 {

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
        String topicName = "ak02.hw.translog";
        // 步驟4. 讓Consumer向Kafka集群訂閱指定的topic
        consumer.subscribe(Arrays.asList(topicName), new SeekToListener(consumer)); // ** Tips: 讓這支程式每次重起時都把offset移到最前面的SeekToListener
        // 步驟5. 持續的拉取Kafka有進來的訊息
        int record_count = 0;

        // 產生一個Map: key是part_no, value是qty balance <---- 存放 “題目#15” 庫存異動數的容器
        Map<String, Integer> parts_transQtyBalance = new HashMap<>();

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
                    String msgValue = record.value(); // << 這個是商品貨物交易資料

                    // ** “題目#15” 的主要邏輯 **

                    // *** 你要開發的Block在這裡 [Start] ***

                    // Tips: 在Java中把(key,value)放進"Map"的collection, 使用的method是 map_object.put(key, value)
                    //       把某個key的value從map物件取出來所使用的method是 map_object.get(key)

                    // Tips: 解析msgValue, 根據spec: msgValue是交易的內容(例如 "+|123" , 資料用"|"做分隔
                    //                        第一個欄位"+"代表是庫存量的增加, "-"代表庫存量的減少
                    //                        第二個欄位代表庫存的異動量
                    String[] elements = msgValue.split("\\|");

                    String opType = elements[0]; // "+"代表是庫存量的增加, "-"代表庫存量的減少
                    int trnsQty = Integer.parseInt(elements[1]); // 庫存的異動量

                    //檢查看Map中是否存在msgKey
                    if(parts_transQtyBalance.containsKey(msgKey)){
                        // 把之前的筆數拿回來, 做完trans之後再放回
                        int transQtyBalance = parts_transQtyBalance.get(msgKey);

                        // 檢查opertion type是'+'或'-'
                        if(opType.equals("+"))
                            parts_transQtyBalance.put(msgKey, transQtyBalance+trnsQty);
                        else
                            parts_transQtyBalance.put(msgKey, transQtyBalance-trnsQty);
                    } else {
                        // 這是第1筆, 設定初始值
                        int init_qty = 0;
                        // 檢查opertion type是'+'或'-'
                        if(opType.equals("+"))
                            parts_transQtyBalance.put(msgKey, init_qty+trnsQty);
                        else
                            parts_transQtyBalance.put(msgKey, init_qty-trnsQty);
                    }
                    // *** 你要開發的Block在這裡 [End] ***
                }
                // 計時結束
                long time_spend = System.nanoTime() - time_start;
                // 打印累積讀取筆數與每次迭代所花費的處理時間
                System.out.println("");
                System.out.println("Accm. Record count: " + record_count + ", Accm. Time spends:" + (System.nanoTime()-gtime_start) + " nano seconds, Time spends: " + time_spend + " nano seconds");
                // 打印出最更新的結果 <---- “題目#15” 的答案
                System.out.println("Parts transQtyBalance: " + mapToString(parts_transQtyBalance));

                // ** 觀察產生的"Accm. Record count"的數值, 如果沒有持續增加就代表
                //    程式己經把現在topic裡有的訊息全部都收進來並且你看到的結果就是問題的答案 **
            }
        } finally {
            // 步驟6. 如果收到結束程式的訊號時關掉Consumer實例的連線
            consumer.close();
            System.out.println("Stop listen incoming messages");
        }
    }

    // 一個簡單用來對Map容器進行Key值排序的函式
    private static List<String> sortingMap(Map<String, Integer> datas) {
        List<String> sorted_result = new ArrayList<>(datas.keySet());
        Collections.sort(sorted_result);
        return sorted_result;
    }

    // 一個簡單用來對Map容器進行Key值排序來打印其內容的函式
    private static String mapToString(Map<String, Integer> datas) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        List<String> sortedKeys = sortingMap(datas);
        int elements_count = 0;
        for(String key : sortingMap(datas)){
            elements_count++;
            sb.append(key);
            sb.append("=");
            sb.append(datas.get(key));
            if(elements_count!=sortedKeys.size())
                sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
