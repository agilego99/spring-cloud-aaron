package com.wistron.witlab;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 用來發佈ak04作業的範例程式
 */

public class HomeworkAnswerPub {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String WORKSHOP_ID = "01";
    private static String STUDENT_ID = "10708041"; // *** <-- 修改成你/妳的學生編號
                                     //"10708041","10709024","10707057","10704048"

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgValue的序列化器

        // 讓producer降低資料不見、亂序或重覆機率的設定
        props.put("acks","all");
        props.put("max.in.flight.requests.per.connection","1");
        props.put("retries",Integer.MAX_VALUE+"");

        // 步驟2. 產生一個Kafka的Producer的實例
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 步驟3. 指定想要發佈訊息的topic名稱
        String topicName = "ak04.ws" + WORKSHOP_ID + ".homework"; // 個人作業繳交的Topic

        try {
            System.out.println("Start sending ak04 questions/answers messages ...");

            // 步驟4. 產生要發佈到Kafka的訊息 (把訊息封裝進一個ProducerRecord的實例中)
            //    - 參數#1: topicName
            //    - 參數#2: msgKey
            //    - 參數#3: msgValue

            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|1",  "1"));  // 第1題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|2",  "3"));  // 第2題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|3",  "4"));  // 第3題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|4",  "2"));  // 第4題 
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|5",  "2"));  // 第5題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|6",  "5"));  // 第6題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|7",  "5"));  // 第7題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|8",  "1"));  // 第8題 
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|9",  "3"));  // 第9題 
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|10", "1")); // 第10題 
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|11", "3083")); // 第11題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|12", "max=92.0|min=3.0")); // 第12題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|13", "min=2013/01/01 02:28|max=2013/01/16 21:46")); // 第13題
            producer.send(new ProducerRecord<>(topicName, STUDENT_ID+"|14", "2013/01/01=8|2013/01/02=2|2013/01/05=10|2013/01/06=5|2013/01/07=21|2013/01/08=20|2013/01/10=8|2013/01/13=2999|2013/01/14=2|2013/01/15=5|2013/01/16=3")); // 第14題

        } catch (Exception e) {
            // 錯誤處理
            e.printStackTrace();
        }
        // 步驟5. 關掉Producer實例的連線
        producer.close();
        System.out.println("Message sending completed!");
    }
}
