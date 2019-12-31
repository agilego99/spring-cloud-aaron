package com.wistron.witlab;

import com.wistron.witlab.model.Taxidata;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 用來解ak04-q11作業的範例程式
 */
/**
 * Tips:
 * 由於11題主要是要練習以Row(行)的方式讀取CSV並使用由Avro Schema產生的Avro DTO類別Taxidata來做為要拋轉進Kafka的序列化物件。
 * 在這裡我們使用自己寫的序列化方法serializeToByte()來把物件序列化成byte[]然後再拋進Kafka。
 *
 * 這個程式只能跑一次, 不然資料會重覆。如果有問題, 則需要使用kafka-topic的command來移除topic再重拋!
 */
public class Homework_q11 {

    private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
    private static String WORKSHOP_ID = "01";
    private static String STUDENT_ID = "STUDENT_ID"; // *** <-- 修改成你/妳的學生編號

    public static void main(String[] args) throws Exception {
         // 步驟1. 取得Kafka的Producer實例
        final Producer<String, byte[]> producer = getKafkaProducer();

        // 步驟2: 設定要發佈的topic名稱
        String topicName = "ak04.hw." + STUDENT_ID;

        // 步驟3: 讀取讀取CSV的檔案(注意: nyc_taxi_data2.csv必需要在專案的根目錄
        String csv_file = "nyc_taxi_data2.csv";

        int validRecordCount = 0; // 用來計算有效的資料筆數
        int invalidRecordCount = 0; // 用來計算無效的資料筆數
        int rowNumber = 0; // 用來

        // 如何逐行讀取CSV的每一行資料
        try {
            File csv_data = new File(csv_file);
            BufferedReader csv_data_buffer = new BufferedReader(new FileReader(csv_data));
            String lineContent = "";

            // 迭代讀取每一行的資料
            while((lineContent = csv_data_buffer.readLine()) != null) {
                rowNumber++;

                if(rowNumber == 1)
                    continue; // 由於第一行是header, 所以我們要ignore

                // 進行每一行的資料檢查或相對應的處理
                if(lineContent==null || lineContent.isEmpty())
                    invalidRecordCount++;
                else {
                    // 進行字串轉換解析成Avro物件
                    Taxidata taxidata = parseCsvLineToAvro(rowNumber, lineContent);

                    // 檢查taxidata是否為null
                    if(taxidata==null)
                        invalidRecordCount++;
                    else {
                        // 這是一個有效的資料行
                        validRecordCount++;

                         System.out.println(rowNumber-1 + "," + taxidata);
                        RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(
                                topicName,
                                "" + (rowNumber - 1),
                                serializeToByte(taxidata))).get(); // 讓我們使用sync的手法來看每一筆的回應

                        System.out.println("Topic:Partition:Offset: [" + recordMetadata.topic() + "]:["
                                + recordMetadata.partition() + "]:["
                                + recordMetadata.offset() + "] -- Row Number: " + (rowNumber - 1));
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace(); // 有出現問題
        }

        System.out.println("Total CSV records: " + (rowNumber-1));
        System.out.println("Total Valid records: " + validRecordCount);
        System.out.println("Total Invalid records: " + invalidRecordCount);

        // 步驟4. 關掉Producer實例的連線
       producer.close();
        System.out.println("Message sending completed!");
    }

    // 一個用來將Date的文字字串轉換成Date物件的格式
    private static SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    // 一個用來將CSV的文字字串轉換成Avro的物件的函式
    private static Taxidata parseCsvLineToAvro(int rowId, String csvLine) {
        Taxidata taxidata = null;

        // 解析內容 (根據spec應該要有21個欄位值)
        String[] elements = csvLine.split(",");
        // 由於最後一個欄位可能為empty, 因此splits之後結果落在20或21個
        if (elements.length<20 || elements.length>21)
            return null;

        // 進行每個欄位的DataType的轉換與解析
        try {
            String medallion = elements[0];
            String hack_license = elements[1];
            String vendor_id = elements[2];
            String payment_type = elements[3];
            Float fare_amount = Float.parseFloat(elements[4]);
            Float surcharge = Float.parseFloat(elements[5]);
            Float mta_tax = Float.parseFloat(elements[6]);
            Float tip_amount = Float.parseFloat(elements[7]);
            Float tolls_amount = Float.parseFloat(elements[8]);
            Float total_amount = Float.parseFloat(elements[9]);
            Integer rate_code = Integer.parseInt(elements[10]);
            Date pickup_datetime = dateStringFormat.parse(elements[11]);
            Date dropoff_datetime = dateStringFormat.parse(elements[12]);
            Integer passenger_count = Integer.parseInt(elements[13]);
            Integer trip_time_in_secs = Integer.parseInt(elements[14]);
            Float trip_distance = Float.parseFloat(elements[15]);
            Float pickup_longitude = Float.parseFloat(elements[16]);
            Float pickup_latitude = Float.parseFloat(elements[17]);
            Float dropoff_longitude = Float.parseFloat(elements[18]);
            Float dropoff_latitude = Float.parseFloat(elements[19]);
            String credit_card = null;
            if(elements.length==21)
                credit_card = elements[20];

            // 構建Avro的物件實例
            Taxidata.Builder taxidataBuilder = Taxidata.newBuilder();
            taxidataBuilder
                    .setMedallion(medallion)
                    .setHackLicense(hack_license)
                    .setVendorId(vendor_id)
                    .setPaymentType(payment_type)
                    .setFareAmount(fare_amount)
                    .setSurcharge(surcharge)
                    .setMtaTax(mta_tax)
                    .setTipAmount(tip_amount)
                    .setTollsAmount(tolls_amount)
                    .setTotalAmount(total_amount)
                    .setRateCode(rate_code)
                    .setPickupDatetime(pickup_datetime.getTime())
                    .setDropoffDatetime(dropoff_datetime.getTime())
                    .setPassengerCount(passenger_count)
                    .setTripTimeInSecs(trip_time_in_secs)
                    .setTripDistance(trip_distance)
                    .setPickupLongitude(pickup_longitude)
                    .setPickupLatitude(pickup_latitude)
                    .setDropoffLongitude(dropoff_longitude)
                    .setDropoffLatitude(dropoff_latitude)
                    .setCreditCard(credit_card);

            taxidata = taxidataBuilder.build();
        } catch (Exception e) {
            // do nothing, we will return null object back
            System.out.println("RodId: " + rowId +", Reason: " +e.getMessage());
        } finally {
            return taxidata;
        }
    }

    // 產生一個Kafka的Producer物件的實例
    private static Producer<String, byte[]> getKafkaProducer() {
        // 步驟1. 設定要連線到Kafka集群的相關設定
        Properties props = new Properties();

        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer"); // 指定msgValue的序列化器

        props.put("acks","all");
        props.put("max.in.flight.requests.per.connection","1");
        props.put("retries",Integer.MAX_VALUE+"");

        // 步驟2. 產生一個Kafka的Producer的實例
        Producer<String, byte[]> producer = new KafkaProducer<>(props);

        return producer;
    }

    // 一個用來將Avro SpecificRecord物件轉換成byte[]的函式
    private static byte[] serializeToByte(Taxidata data) {
        byte[] result = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 產生一個ByteArrayOutputStream
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);

        DatumWriter<Taxidata> datumWriter = new SpecificDatumWriter<>(Taxidata.class);

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
