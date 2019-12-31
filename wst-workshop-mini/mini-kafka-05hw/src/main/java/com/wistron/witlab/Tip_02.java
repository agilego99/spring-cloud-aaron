package com.wistron.witlab;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.record.TimestampType;

import com.wistron.witlab.model.Taxidata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * 示範如何透過JDBC來讀取一個Mysql資料庫裡某一個資料表的檔案。如果
 */
public class Tip_02 {
	private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // 設定要連接的Kafka群
	private static String WORKSHOP_ID = "01";
	private static String STUDENT_ID = "10707057"; // *** <-- 修改成你/妳的學生編號
	private static String SCHEMA_REGISTRY_URL = "http://10.34.4.109:8081"; // SchemaRegistry的服務在那裡?

	// JDBC driver名稱與database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.34.4.110/ak05";

	// Database帳號密碼
	static final String DB_USER = "ak05";
	static final String DB_PASS = "witlab04";

	public static void main(String[] args) {
    
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
        props.put("group.id", STUDENT_ID); // <-- 這就是ConsumerGroup
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 指定msgKey的反序列化器
        props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer"); // 指定msgValue的反序列化器
        props.put("schema.registry.url", SCHEMA_REGISTRY_URL); // <-- SchemaRegistry的服務在那裡?
        props.put("specific.avro.reader", "true"); // <-- 告訴KafkaAvroDeserializer來反序列成Avro產生的specific物件類別
                                                   //     (如果沒有設定, 則都會以GenericRecord方法反序列)
        props.put("auto.offset.reset", "earliest"); // 是否從這個ConsumerGroup尚未讀取的partition/offset開始讀
        props.put("enable.auto.commit", "false");
    	
       
        Consumer<String, Taxidata> consumer = new KafkaConsumer<>(props); // msgKey是string, msgValue是Employee
        String topicName = "ak05.hw.taxidata.q12";
        consumer.subscribe(Arrays.asList(topicName), new SeekToListener(consumer));
        
    	Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 1: 註冊要使用的JDBC driver名稱
            Class.forName(JDBC_DRIVER);

            //STEP 2: 建立與資料庫的連線
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            conn.setAutoCommit(false); // 關掉交易的AutoCommit

            System.out.println("Connected database successfully...");        

                // 要Insert到資料庫的RECORD_TEMPLATE
              String tableName = "taxidata_"+STUDENT_ID;
              String INSERT_RECORD  = "INSERT INTO " + tableName + " (row_id, medallion, hack_license, vendor_id, payment_type, fare_amount, surcharge, mta_tax, tip_amount, tolls_amount, total_amount, rate_code, pickup_datetime, dropoff_datetime, passenger_count, trip_time_in_secs, trip_distance, pickup_longitude, pickup_latitude, dropoff_longitude, dropoff_latitude, credit_card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
              PreparedStatement pstmt = conn.prepareStatement(INSERT_RECORD);
              Boolean parsing_result = false;  
              String lineContent = "";
                        
              //STEP 3:  從Kafka的Topic中讀取Avro-encode的訊息
              while (true) {
                  System.out.println("Start listen incoming messages ...");
                    // 請求Kafka把新的訊息吐出來
                    ConsumerRecords<String, Taxidata> records = consumer.poll(Duration.ofMillis(1000));
                    // 如果有任何新的訊息就會進到下面的迭代
                    for (ConsumerRecord<String, Taxidata> record : records){
                        // ** 在這裡進行商業邏輯與訊息處理 **
                        // 取出相關的metadata
                        String topic = record.topic();
                        int partition = record.partition();
                        long offset = record.offset();
                        TimestampType timestampType = record.timestampType();
                        long timestamp = record.timestamp();
                        // 取出msgKey與msgValue
                        String msgKey = record.key();
                        Taxidata taxidata = record.value(); //<-- 注意
                        
                    	Integer row_id = taxidata.getRowId();
        				String medallion = taxidata.getMedallion();
        				String hack_license = taxidata.getHackLicense();
        				String vendor_id = taxidata.getVendorId();
        				String payment_type = taxidata.getPaymentType();
        				Float fare_amount = taxidata.getFareAmount();
        				Float surcharge = taxidata.getSurcharge();
        				Float mta_tax = taxidata.getMtaTax();
        				Float tip_amount = taxidata.getTipAmount();
        				Float tolls_amount = taxidata.getTollsAmount();
        				Float total_amount = taxidata.getTotalAmount();
        				Integer rate_code = taxidata.getRateCode();
        				long pickup_datetime = taxidata.getPickupDatetime();
        				long dropoff_datetime =  taxidata.getDropoffDatetime();
        				Integer passenger_count = taxidata.getPassengerCount();
        				Integer trip_time_in_secs = taxidata.getTripTimeInSecs();
        				Float trip_distance = taxidata.getTripDistance();
        				Float pickup_longitude = taxidata.getPickupLongitude();
        				Float pickup_latitude = taxidata.getPickupLatitude();
        				Float dropoff_longitude = taxidata.getDropoffLongitude();
        				Float dropoff_latitude = taxidata.getDropoffLatitude();
        				String credit_card = taxidata.getCreditCard();
        				
        			
        				lineContent =  
        		        	  medallion + ","
        		        	+ hack_license + ","
        		        	+ vendor_id + ","
        		        	+ payment_type + ","
        		        	+ fare_amount + ","
        		        	+ surcharge + ","
        		        	+ mta_tax + ","
        		        	+ tip_amount + "," 
        		        	+ tolls_amount + ","
        		        	+ total_amount + ","
        		        	+ rate_code + ","
        		        	+ pickup_datetime + ","
        		        	+ dropoff_datetime + ","
        		        	+ passenger_count + ","
        		        	+ trip_time_in_secs + ","
        		        	+ trip_distance + ","
        		        	+ pickup_longitude + ","
        		        	+ pickup_latitude + ","
        		        	+ dropoff_longitude + ","
        		        	+ dropoff_latitude + ","
        		        	+ credit_card ;
        				
                        parsing_result = parseCsvLineToSqlStatement(pstmt, row_id, lineContent);
                        System.err.println(parsing_result +"\t" + lineContent);
                        consumer.commitAsync();     
                       }

                    pstmt.executeBatch(); // <<---- 我們使用批次commit的方法來降低要跟Database往返的呼叫次數
                   conn.commit(); // <<---- 記得要commit交易

                   ResultSet result = pstmt.executeQuery("Select count(*) from "  + "taxidata_10707057");
                   result.next();
                   int foundType = result.getInt(1);
                   System.err.println(foundType);
  		
            }           
              
              
            

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
   }

	// 一個用來將Date的文字字串轉換成Date物件的格式
	private static SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	// 一個用來將CSV的文字字串轉換成Avro的物件的函式
	private static boolean parseCsvLineToSqlStatement(PreparedStatement pstmt, int rowId, String csvLine) {
		String rowID = rowId + "";

		// 解析內容 (根據spec應該要有21個欄位值)
		String[] elements = csvLine.split(",");
		// 由於最後一個欄位可能為empty, 因此splits之後結果落在20或21個
		if (elements.length < 20 || elements.length > 21) {
			System.err.println(elements.length);
			return false;
		}

//		for(int i= 0 ; i< elements.length ; i++) {
//			System.err.println(elements.length);
//			System.err.println(elements[i]);
//		}
		
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
			// 在SQL中有定義三種常用跟時間有關的資料型別:
			// java.sql.Date: Mapping for SQL DATE.
			// java.sql.Time: Mapping for SQL TIME.
			// java.sql.Timestamp: Mapping for SQL TIMESTAMP.
			//
			// ** 這三種資料型別跟一般在Java中所使用的java.util.Date的型別是不一樣的資料型別, 因此需要進行轉換(casting) **
			// ** 同時在進行從時間字串解析成Java的java.util.Date的時候, 還要注意”時區(time-zone)"的問題 **
			// ** 在資料的處理過程為了讓資料的時間具有一致性, 我們會把來源的時間都轉換成"UTC”的時區

			Timestamp pickup_datetime =  new Timestamp(Long.parseLong(elements[11]));
			Timestamp dropoff_datetime = new Timestamp(Long.parseLong(elements[12]));

			Integer passenger_count = Integer.parseInt(elements[13]);
			Integer trip_time_in_secs = Integer.parseInt(elements[14]);
			Float trip_distance = Float.parseFloat(elements[15]);
			Float pickup_longitude = Float.parseFloat(elements[16]);
			Float pickup_latitude = Float.parseFloat(elements[17]);
			Float dropoff_longitude = Float.parseFloat(elements[18]);
			Float dropoff_latitude = Float.parseFloat(elements[19]);
			String credit_card = null;
			if (elements.length == 21)
				credit_card = elements[20];

			pstmt.setInt(1, rowId - 1);
			pstmt.setString(2, medallion);
			pstmt.setString(3, hack_license);
			pstmt.setString(4, vendor_id);
			pstmt.setString(5, payment_type);
			pstmt.setFloat(6, fare_amount);
			pstmt.setFloat(7, surcharge);
			pstmt.setFloat(8, mta_tax);
			pstmt.setFloat(9, tip_amount);
			pstmt.setFloat(10, tolls_amount);
			pstmt.setFloat(11, total_amount);
			pstmt.setInt(12, rate_code);
			pstmt.setTimestamp(13, pickup_datetime);
			pstmt.setTimestamp(14, dropoff_datetime);
			pstmt.setInt(15, passenger_count);
			pstmt.setInt(16, trip_time_in_secs);
			pstmt.setFloat(17, trip_distance);
			pstmt.setFloat(18, pickup_longitude);
			pstmt.setFloat(19, pickup_latitude);
			pstmt.setFloat(20, dropoff_longitude);
			pstmt.setFloat(21, dropoff_latitude);
			pstmt.setString(22, credit_card);

		   // 把這個record加入到batch中
			pstmt.addBatch(); // <--- 透過批次的傳送可以大幅度增加throught-put

			return true;
		} catch (Exception e) {
			// do nothing, we will return null object back
			System.out.println("RodId: " + rowId + ", Reason: " + e.getMessage());
			return false; // 當有問題時return false
		}
	}
}
