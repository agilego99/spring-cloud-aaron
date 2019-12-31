package com.wistron.witlab;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.wistron.witlab.model.Taxidata;

/**
 * 示範如何透過JDBC來讀取一個Mysql資料庫裡某一個資料表的檔案
 */
public class Tip_01 {

	private static String KAFKA_BROKER_URL = "10.34.4.109:9092"; // Kafka集群在那裡?
	private static String SCHEMA_REGISTRY_URL = "http://10.34.4.109:8081"; // SchemaRegistry的服務在那裡?
	
	// JDBC driver名稱與database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.34.4.110/ak05";

	// Database帳號密碼
	static final String DB_USER = "ak05";
	static final String DB_PASS = "witlab04";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		 Properties props = new Properties();
	        props.put("bootstrap.servers", KAFKA_BROKER_URL); // Kafka集群在那裡?
	        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // 指定msgKey的序列化器
	        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer"); // <-- 指定msgValue的序列化器
	        props.put("schema.registry.url", SCHEMA_REGISTRY_URL);// SchemaRegistry的服務在那裡?
	        props.put("acks","all");
	        props.put("max.in.flight.requests.per.connection","1");
	        props.put("retries",Integer.MAX_VALUE+"");
	        // 步驟2. 產生一個Kafka的Producer的實例 <-- 注意
		
	        Producer<String, Taxidata> producer = new KafkaProducer<>(props);  // msgKey是string, msgValue是Taxidata
	
		try {
			// STEP 1: 註冊要使用的JDBC driver名稱
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 2: 建立與資料庫的連線
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

			// STEP 3: 執行query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM taxidata order by row_id ASC";
			ResultSet rs = stmt.executeQuery(sql);
			

			// STEP 4: 從Query的結果集中取出要使用的欄位資料
			while (rs.next()) {
			
				// 使用欄位名稱來把值取出來(注意每個欄位在Table的資料型別
				Integer row_id = rs.getInt("row_id");
				String medallion = rs.getString("medallion");
				String hack_license = rs.getString("hack_license");
				String vendor_id = rs.getString("vendor_id");
				String payment_type = rs.getString("payment_type");
				Float fare_amount = rs.getFloat("fare_amount");
				Float surcharge = rs.getFloat("surcharge");
				Float mta_tax = rs.getFloat("mta_tax");
				Float tip_amount = rs.getFloat("tip_amount");
				Float tolls_amount = rs.getFloat("tolls_amount");
				Float total_amount = rs.getFloat("total_amount");
				Integer rate_code = rs.getInt("rate_code");
				Timestamp pickup_datetime = rs.getTimestamp("pickup_datetime");
				Timestamp dropoff_datetime = rs.getTimestamp("dropoff_datetime");
				Integer passenger_count = rs.getInt("passenger_count");
				Integer trip_time_in_secs = rs.getInt("trip_time_in_secs");
				Float trip_distance = rs.getFloat("trip_distance");
				Float pickup_longitude = rs.getFloat("pickup_longitude");
				Float pickup_latitude = rs.getFloat("pickup_latitude");
				Float dropoff_longitude = rs.getFloat("dropoff_longitude");
				Float dropoff_latitude = rs.getFloat("dropoff_latitude");
				String credit_card = rs.getString("credit_card");

				// STEP 5: 把資料庫的每一筆資料轉換成Avro的Record
				// ...
				Taxidata taxidata = Taxidata.newBuilder()
						.setRowId(row_id)
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
						.setCreditCard(credit_card).build();

	
				// STEP 6: 把Avro的Record發佈到指定的Kafka Topic中
				// ...

				RecordMetadata metaData = producer.send(new ProducerRecord<String, Taxidata>("ak05.hw.taxidata.工號在這", "" + row_id, taxidata))
//						.get(); // msgKey是string, msgValue是Taxidata
				
				// STEP 7: 打印出來看一下(以下的打印只是示範)
				List<Object> fields = Arrays.asList(row_id, medallion, hack_license, vendor_id, vendor_id, payment_type,
						fare_amount, surcharge, mta_tax, tip_amount, tolls_amount, total_amount, rate_code,
						pickup_datetime, dropoff_datetime, passenger_count, trip_time_in_secs, trip_distance,
						pickup_longitude, pickup_latitude, dropoff_longitude, dropoff_latitude, credit_card);

				System.err.println(fields);
			}
			// STEP 8: 清理環境, 関閉與資料庫的連線
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// Exception處理
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}
}