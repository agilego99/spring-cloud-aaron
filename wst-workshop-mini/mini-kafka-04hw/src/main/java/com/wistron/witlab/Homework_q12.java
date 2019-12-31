package com.wistron.witlab;

import com.wistron.witlab.model.Taxidata;
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
 * 用來解ak04-q12作業的範例程式
 */
/**
 * Tips:
 * 12題主要是透過Avro來把byte[] 反序列化成物件, 然後再要練習如何利用簡單的判斷來得到某種觀測值的”最大值"與"最小值"。同時透過過濾的手法在
 * 串流資料中得到想要處理的資料。
 * （Select Max(observe_value), Min(observe_value) From xxx Where a='yy' and b='zz' Group By field_A ...)
 */
public class Homework_q12 {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String WORKSHOP_ID = "01";
    private static String STUDENT_ID = "STUDENT_ID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();

        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("group.id", STUDENT_ID); // <-- 這就是ConsumerGroup
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgKey的反序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer"); // 指定msgValue的反序列化器
        props.put("auto.offset.reset", "earliest"); // 是否從這個ConsumerGroup尚未讀取的partition/offset開始讀

        // 步驟2. 產生一個Kafka的Consumer的實例
        Consumer<String, byte[]> consumer = new KafkaConsumer<>(props); // msgKey是string, msgValue是byte[]

        // 步驟3. 指定想要訂閱訊息的topic名稱
        String topicName = "ak04.hw." + STUDENT_ID;

        // 步驟4. 讓Consumer向Kafka集群訂閱指定的topic (透過SeekToListener可以重覆地reset ConsumerGroup的offset回到每個partition的最前頭)
        consumer.subscribe(Arrays.asList(topicName), new SeekToListener(consumer));
        int recordCounter = 0; // 用來累積收到的record數

        // 用來記錄最大與最小的"fare_amount"
        Float min_fare_amount = null;
        Float max_fare_amount = null;

        // 步驟5. 持續的拉取Kafka有進來的訊息
        try {
            System.out.println("Start listen incoming messages ...");
            while (true) {
                // 請求Kafka把新的訊息吐出來
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(1000));
                // 如果有任何新的訊息就會進到下面的迭代
                for (ConsumerRecord<String, byte[]> record : records){
                    recordCounter++;

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
                    Taxidata taxidata =  deserializeToRecord(msgValue);
                    // 根據題義取出以下3個欄位vendor_id, payment_type及fare_amount
                    String vendor_id = taxidata.getVendorId();
                    String payment_type = taxidata.getPaymentType();
                    Float fare_amount = taxidata.getFareAmount();

                    // 秀出metadata與msgKey & msgValue訊息
                    //System.out.println(topic + "-" + partition + "-" + offset + " : (" + record.key() + ", " + taxidata + ")");

                    // *** 你要開發的Block在這裡 [Start] ***

                    // ...
                    // ...

                    // *** 你要開發的Block在這裡 [End] ***
                }
                // 步驟6. 每次拉到一批次的資料就進行處理與打印
                System.out.println("Total received records: " + recordCounter);
                System.out.println("fare_amount-Min: " + min_fare_amount);
                System.out.println("fare_amount-Max: " + max_fare_amount);
            }
        } finally {
            // 步驟7. 如果收到結束程式的訊號時關掉Consumer實例的連線
            consumer.close();
            System.out.println("Stop listen incoming messages");
        }
    }

    // 一個用來將Avro序列化後的byte[]再反序列化成GenericRecord的函式
    private static Taxidata deserializeToRecord(byte[] bytes) {
        DatumReader<Taxidata> datumReader = new SpecificDatumReader<>(Taxidata.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        Taxidata record = null;
        try {
            record = datumReader.read(null, decoder);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return record;
        }
    }
}
