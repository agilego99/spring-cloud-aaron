package com.wistron.witlab.ak02hw;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

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
public class Homework_q02_pub {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgValue的序列化器
        // 步驟2. 產生一個Kafka的Producer的實例
        Producer<String, String> producer = new KafkaProducer<>(props);
        // 步驟3. 指定想要發佈訊息的topic名稱
        // 產生一個有5個partitions的topic:
        //    kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic test5
        String topicName = "test5";
        int msgCounter = 0;
        try {
            System.out.println("Start sending messages ...");
            // 步驟4. 產生要發佈到Kafka的訊息 (把訊息封裝進一個ProducerRecord的實例中)
            //    - 參數#1: topicName
            //    - 參數#2: msgKey
            //    - 參數#3: msgValue
            producer.send(new ProducerRecord<>(topicName, "whereami", "test"));
            msgCounter++;
            System.out.println("Send " + msgCounter + " messages to Kafka");
        } catch (Exception e) {
            // 錯誤處理
            e.printStackTrace();
        }
        // 步驟5. 關掉Producer實例的連線
        producer.close();
        System.out.println("Message sending completed!");
    }
}
