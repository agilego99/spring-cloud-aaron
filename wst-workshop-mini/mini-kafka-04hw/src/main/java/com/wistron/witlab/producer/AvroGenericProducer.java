package com.wistron.witlab.producer;

import org.apache.avro.Schema;
import org.apache.avro.generic.*;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 示範如何使用Avro來傳送資料進Kafka
 */
public class AvroGenericProducer {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String STUDENT_ID = "STUDENT_ID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer"); // <-- 指定msgValue的序列化器
        props.put("acks","all");
        props.put("max.in.flight.requests.per.connection","1");
        props.put("retries",Integer.MAX_VALUE+"");

        // 步驟2. 產生一個Kafka的Producer的實例 <-- 注意
        Producer<String, byte[]> producer = new KafkaProducer<>(props);  // msgKey是string, msgValue是byte[]

        // 步驟3. 指定想要發佈訊息的topic名稱
        String topicName = "ak04.test."+STUDENT_ID;

        // 步驟5. 取得Avro的schema (在resources/avro/customer.avsc)
        Schema schema = getAvroSchema();
        // 步驟6. 產生一個avro "generic record" 物件來做為資料的容器
        // 讓我們產生第一個customer的record

        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema); // 把schema傳入
        customerBuilder.set("id","007");
        customerBuilder.set("first_name", "John");
        customerBuilder.set("last_name", "Doe");
        customerBuilder.set("age", 26);
        customerBuilder.set("height", 175f);
        customerBuilder.set("weight", 70.5f);
        customerBuilder.set("automated_email", false);
        customerBuilder.set("create_dt", System.currentTimeMillis()); // 取得現在系統時間

        GenericData.Record myCustomer = customerBuilder.build();

        // 打印出來看一下
        System.out.println(myCustomer);
        // 步驟7 .將Avro GenericRecord 序列化(serialize)成為byte[]
        byte[] customer_as_bytes = serializeToByte(myCustomer);
        try {
            System.out.println("Start sending messages ...");

            // 步驟8. 產生要發佈到Kafka的訊息 (把訊息封裝進一個ProducerRecord的實例中)
            //    - 參數#1: topicName
            //    - 參數#2: msgKey
            //    - 參數#3: msgValue
            producer.send(new ProducerRecord<String, byte[]>(topicName, (String) myCustomer.get("id"), customer_as_bytes)); // msgKey是string, msgValue是byte[]
            System.out.println("Send " + myCustomer + " messages to Kafka");
        } catch (Exception e) {
            // 錯誤處理
            e.printStackTrace();
        }
        producer.flush();
        producer.close();
    }

    // 一個用來取得Avro schema的函式
    private static Schema getAvroSchema(){
        // 步驟5. 取得Avro的schema
        Schema.Parser parser = new Schema.Parser(); // 產生一個Avro的schema parser物件

        // 定義avro的schema (在resources/avro/customer.avsc)
        Schema schema = parser.parse("{\n" +
                "     \"type\": \"record\",\n" +
                "     \"namespace\": \"com.wistron.witlab.model\",\n" +
                "     \"name\": \"Customer\",\n" +
                "     \"fields\": [\n" +
                "       { \"name\": \"id\", \"type\": \"string\"},\n" +
                "       { \"name\": \"first_name\", \"type\": \"string\"},\n" +
                "       { \"name\": \"last_name\", \"type\": \"string\"},\n" +
                "       { \"name\": \"age\", \"type\": \"int\"},\n" +
                "       { \"name\": \"height\", \"type\": \"float\"},\n" +
                "       { \"name\": \"weight\", \"type\": \"float\"},\n" +
                "       { \"name\": \"automated_email\", \"type\": \"boolean\", \"default\": true},\n" +
                "       { \"name\": \"create_dt\", \"type\": \"long\", \"logicalType\": \"timestamp-millis\"}\n" +
                "     ]\n" +
                "}");


        return schema;
    }

    // 一個用來將Avro GenericRecord物件轉換成byte[]的函式
    private static byte[] serializeToByte(GenericRecord data) {
        byte[] result = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 產生一個ByteArrayOutputStream
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(data.getSchema());

        try {
            datumWriter.write(data, binaryEncoder);
            binaryEncoder.flush();
            byteArrayOutputStream.close();
            result = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
