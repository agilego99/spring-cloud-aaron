package com.wistron.witlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 示範如何解析一個用","隔的字串
 */
public class Tip_QA_2 {
    public static void main(String[] args) throws JsonProcessingException {
        //除了"1"的資料是完整的, 其餘2,3都在解析時會出現問題
        HashMap<String, String> fakeTopicData = new HashMap<>();
        fakeTopicData.put("1","BE386D8524FCD16B3727DCF0A32D9B25,4EB96EC9F3A42794DEE233EC8A2616CE,VTS,CRD,12,0.5,0.5,3.12,0,16.12,1,2013/1/13 04:37,2013/1/13 04:48,2,660,3.39,-74.000313,40.730068,-73.987373,40.768406,5.16329E+15");
        fakeTopicData.put("2","F6F7D02179BE915B23EF2DB57836442D,088879B44B80CC9ED43724776C539370,VTS,CRD,12,0.5,0.5,1.75,0,14.75,1,2013-01-13 04:36asdf,2013-01-13 04:46asdf,5,600,3.12,-73.996933,40.720055,-73.993546,40.693043,4.02401E+15");
        fakeTopicData.put("3",",,,,,,,,,,,,,,,,,,,,");


        int recordCounter = 0; // 用來累積收到的record數
        int validRecordCount = 0; // 用來計算有效的資料筆數
        int invalidRecordCount = 0; // 用來計算無效的資料筆數

        // 一個用來計錄那些row_number為不符合規定的容器 (key: 是row number, value: 是error message)
        Map<String, String> invalid_records = new HashMap<>();

        // 一個用來將DTO物件轉換成(JSON String)的物件 <-- 重要
        ObjectMapper om = new ObjectMapper();

        // 一個用來將Date的文字字串轉換成Date物件的格式
        SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        // 模擬從Kafka topic中拉到資料
        for(Map.Entry<String, String> entry : fakeTopicData.entrySet()) {
            recordCounter++;

            String msgKey = entry.getKey();
            String msgValue = entry.getValue();

            // 檢查資料是否有值且不能空白
            if(msgValue==null || msgKey.isEmpty()) {
                // 記錄
                invalidRecordCount++;
                invalid_records.put(msgKey, "Record is either null or empty");
                continue;
            }

            // 解析內容 (應該要有21個欄位值
            String[] elements = msgValue.split(",");

            if (elements.length<20 || elements.length>21){
                // 記錄
                invalidRecordCount++;
                invalid_records.put(msgKey, "Record elements size is incorrect");
                continue;
            }
            /*
            // 進行每個欄位的DataType的轉換與解析
            Taxidata taxidata = null;

            try{
                String row_id = msgKey;
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

                // 建立Taxidata的實例
                taxidata = new Taxidata(row_id, medallion, hack_license, vendor_id, payment_type,
                        fare_amount, surcharge, mta_tax, tip_amount, tolls_amount, total_amount, rate_code,
                        pickup_datetime, dropoff_datetime, passenger_count, trip_time_in_secs, trip_distance,
                        pickup_longitude, pickup_latitude, dropoff_longitude, dropoff_latitude, credit_card);

            }catch (Exception e){
                // 記錄
                invalidRecordCount++;
                invalid_records.put(msgKey, e.getMessage());
                continue;
            }

            // 將DTO(Data transfer object)序列化成JSON字串
            System.out.println(om.writeValueAsString(taxidata));

            validRecordCount++;


        */
        }

        // 打印結果
        System.out.println("Total received records: " + recordCounter);
        System.out.println("Total Valid records: " + validRecordCount);
        System.out.println("Total Invalid records: " + invalidRecordCount);

    }
}
