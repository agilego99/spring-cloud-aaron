package com.wistron.witlab;

import com.wistron.witlab.model.Employee;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 示範如何使用SchemaRegistry與KafkaAvroSerializer來傳送資料進Kafka
 */
public class EmployeeV2Producer {
    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // Kafka集群在那裡?
    private static String SCHEMA_REGISTRY_URL = "http://10.34.4.109:8081"; // SchemaRegistry的服務在那裡?
    private static String STUDENT_ID = "10708041"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();

        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer"); // <-- 指定msgValue的序列化器

        props.put("schema.registry.url", SCHEMA_REGISTRY_URL);// SchemaRegistry的服務在那裡?

        props.put("acks","all");
        props.put("max.in.flight.requests.per.connection","1");
        props.put("retries",Integer.MAX_VALUE+"");

        // 步驟2. 產生一個Kafka的Producer的實例 <-- 注意
        Producer<String, Employee> producer = new KafkaProducer<>(props);  // msgKey是string, msgValue是Employee物件

        // 步驟3. 指定想要發佈訊息的topic名稱
        String topicName = "ak05."+STUDENT_ID+".employee";

        try {

            // 步驟4. 直接使用Maven從scheam產生出來的物件來做為資料的容器

            // 送進第4個員工(schema v2)
            Employee employee = Employee.newBuilder()
                    .setId("10309016")
                    .setName("River Chien")
                    .setAge(25)
                    .setPhone("8501-5221")
                    .setAddress("test")
                    .build();

            RecordMetadata metaData = producer.send(new ProducerRecord<String, Employee>(topicName, employee.getId(), employee)).get(); // msgKey是string, msgValue是Employee
            System.out.println(metaData.offset() + " --> " + employee);

            // 送進第5個員工(schema v2)
            employee = Employee.newBuilder()
                    .setId("10211103")
                    .setName("Sean Lo")
                    .setAge(26)
                    .setPhone("8511-2868")
                    .setAddress("test")
                    .build();

            metaData = producer.send(new ProducerRecord<String, Employee>(topicName, employee.getId(), employee)).get(); // msgKey是string, msgValue是Employee
            System.out.println(metaData.offset() + " --> " + employee);

            // 送進第6個員工(schema v2)
            employee = Employee.newBuilder()
                    .setId("10211103")
                    .setName("Ron Wei")
                    .setAge(27)
                    .setPhone("8501-7333")
                    .setAddress("test")
                    .build();

            metaData = producer.send(new ProducerRecord<String, Employee>(topicName, employee.getId(), employee)).get(); // msgKey是string, msgValue是Employee
            System.out.println(metaData.offset() + " --> " + employee);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
