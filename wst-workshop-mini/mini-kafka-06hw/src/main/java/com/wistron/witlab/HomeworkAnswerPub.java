package com.wistron.witlab;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 用來發佈ak06作業的範例程式
 */
public class HomeworkAnswerPub {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String WORKSHOP_ID = "01";
    private static String STUDENT_ID = "STUDENT_ID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgValue的序列化器
        // 讓producer降低資料不見、亂序或重覆機率的設定
        props.put("enable.idempotence","true");
        props.put("acks","all");
        props.put("max.in.flight.requests.per.connection","1");
        props.put("retries",5);

        // 步驟2. 產生一個Kafka的Producer的實例
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 步驟3. 指定想要發佈訊息的topic名稱
        String topicName = "ak06.ws" + WORKSHOP_ID + ".homework"; // 個人作業繳交的Topic

        try {
            System.out.println("Start sending ak05 questions/answers messages ...");

            // 步驟4. 產生要發佈到Kafka的訊息 (把訊息封裝進一個ProducerRecord的實例中)
            //    - 參數#1: topicName
            //    - 參數#2: msgKey
            //    - 參數#3: msgValue

            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|1",  "0"));  // 第1題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|2",  "0"));  // 第2題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|3",  "0"));  // 第3題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|4",  "0"));  // 第4題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|5",  "0"));  // 第5題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|6",  "0"));  // 第6題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|7",  "0"));  // 第7題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|8",  "0"));  // 第8題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|9",  "0"));  // 第9題
            //producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|10", "0")); // 第10題

        } catch (Exception e) {
            // 錯誤處理
            e.printStackTrace();
        }

        // 步驟5. 關掉Producer實例的連線
        producer.close();

        System.out.println("Message sending completed!");
    }
}
