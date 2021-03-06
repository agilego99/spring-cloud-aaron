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
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * 用來解ak04-q13作業的範例程式
 */
public class Homework_q13 {

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

        // 用來記錄最大與最小的"pickup_datetime"
        Date min_pickup_datetime = null;
        String min_row_number = "";

        Date max_pickup_datetime = null;
        String max_row_number = "";

        // 一個用來將Date的文字字串轉換成Date物件的格式
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

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

                    // 根據題義取出欄位pickup_datetime
                    Long pickup_datetime = taxidata.getPickupDatetime(); // 在AVRO中是用long來保存時間
                    Date pickup_date = new Date(pickup_datetime); // 把epoch (1970/1/1 00:00:00)至今的總milli-secs轉換成Date物件

                    // 秀出metadata與msgKey & msgValue訊息
                    //System.out.println(topic + "-" + partition + "-" + offset + " : (" + record.key() + ", " + taxidata + ")");

                    // *** 你要開發的Block在這裡 [Start] ***

                    // ...
                    // ...

                    // *** 你要開發的Block在這裡 [End] ***
                }

                // 步驟6. 每次拉到一批次的資料就進行處理與打印
                System.out.println("Total received records: " + recordCounter);
                System.out.println("pickup_datetime-Min: " + min_row_number + ":" + dateStringFormat.format(min_pickup_datetime));
                System.out.println("pickup_datetime-Max: " + max_row_number + ":" + dateStringFormat.format(max_pickup_datetime));
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
