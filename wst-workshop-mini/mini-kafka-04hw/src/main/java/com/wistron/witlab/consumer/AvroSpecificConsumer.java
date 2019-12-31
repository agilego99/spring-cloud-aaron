package com.wistron.witlab.consumer;

import com.wistron.witlab.model.Customer;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.record.TimestampType;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 示範如何從Kafka中讀取Avro的資料
 */
public class AvroSpecificConsumer {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String STUDENT_ID = "STUDENT_ID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("group.id", STUDENT_ID); // <-- 這就是ConsumerGroup
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgKey的反序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer"); // 指定msgValue的反序列化器
        props.put("auto.offset.reset", "earliest"); // 是否從這個ConsumerGroup尚未讀取的partition/offset開始讀
        props.put("enable.auto.commit", "false");

        // 步驟2. 產生一個Kafka的Consumer的實例
        Consumer<String, byte[]> consumer = new KafkaConsumer<>(props); // msgKey是string, msgValue是byte[]

        // 步驟3. 指定想要訂閱訊息的topic名稱
        String topicName = "ak04.test."+STUDENT_ID;

        // 步驟4. 讓Consumer向Kafka集群訂閱指定的topic
        consumer.subscribe(Arrays.asList(topicName));
        // 步驟5. 持續的拉取Kafka有進來的訊息
        try {
            System.out.println("Start listen incoming messages ...");
            while (true) {
                // 請求Kafka把新的訊息吐出來
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(1000));
                // 如果有任何新的訊息就會進到下面的迭代
                for (ConsumerRecord<String, byte[]> record : records){
                    // ** 在這裡進行商業邏輯與訊息處理 **
                    // 取出相關的metadata
                    String topic = record.topic();
                    int partition = record.partition();
                    long offset = record.offset();
                    TimestampType timestampType = record.timestampType();
                    long timestamp = record.timestamp();
                    // 取出msgKey與msgValue
                    String msgKey = record.key();
                    byte[] msgValue = record.value(); //<-- 注意
                    // 將Avro序列化後的byte[]再反序列化成GenericRecord
                    Customer data =  deserializeToRecord(msgValue);
                    // 秀出metadata與msgKey & msgValue訊息
                    System.out.println(topic + "-" + partition + "-" + offset + " : (" + record.key() + ", " + data + ")");
                }
                consumer.commitAsync();
            }
        } finally {
            // 步驟6. 如果收到結束程式的訊號時關掉Consumer實例的連線
            consumer.close();
            System.out.println("Stop listen incoming messages");
        }
    }

    // 一個用來將Avro序列化後的byte[]再反序列化成GenericRecord的函式
    private static Customer deserializeToRecord(byte[] bytes) {
        DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        Customer record = null;
        try {
            record = datumReader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return record;
        }
    }
}
