package com.wistron.witlab.producer;

import com.wistron.witlab.model.Customer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 示範如何使用Avro來傳送資料進Kafka
 */
public class AvroSpecificProducer {

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

        // 步驟4. 直接使用Maven從scheam產生出來的物件來做為資料的容器

        // 現在我們可以安全且輕鬆地構建DTO物件
        Customer.Builder customerBuilder = Customer.newBuilder();

        customerBuilder.setId("008")
                .setAge(30)
                .setFirstName("Mark")
                .setLastName("Simpson")
                .setAutomatedEmail(true)
                .setHeight(180f)
                .setWeight(90f)
                .setCreateDt(System.currentTimeMillis());

        Customer customer = customerBuilder.build();

        // 步驟5 .將Avro SpecificRecord 序列化(serialize)成為byte[]
        byte[] customer_as_bytes = serializeToByte(customer);

        try {
            System.out.println("Start sending messages ...");

            // 步驟8. 產生要發佈到Kafka的訊息 (把訊息封裝進一個ProducerRecord的實例中)
            //    - 參數#1: topicName
            //    - 參數#2: msgKey
            //    - 參數#3: msgValue

            producer.send(new ProducerRecord<String, byte[]>(topicName, customer.getId(), customer_as_bytes)); // msgKey是string, msgValue是byte[]

            System.out.println("Send " + customer + " messages to Kafka");

        } catch (Exception e) {
            // 錯誤處理
            e.printStackTrace();
        }

        producer.flush();
        producer.close();
    }

    // 一個用來將Avro SpecificRecord物件轉換成byte[]的函式
    private static byte[] serializeToByte(Customer data) {
        byte[] result = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 產生一個ByteArrayOutputStream
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);

        DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

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
