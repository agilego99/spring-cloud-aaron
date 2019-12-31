package com.wistron.witlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 示範如何逐行讀取一個CSV的檔案
 */
public class Tip_QA_1 {
    public static void main(String[] args) {
        // 讀取CSV的檔案(注意: nyc_taxi_data.csv必需要在專案的根目錄
        String csv_file = "nyc_taxi_data2.csv";
        // 如何逐行讀取CSV的每一行資料
        try {
            File csv_data = new File(csv_file);
            BufferedReader csv_data_buffer = new BufferedReader(new FileReader(csv_data));
            String readLine = "";
            // 迭代讀取每一行的資料
            while((readLine = csv_data_buffer.readLine()) != null) {
                // *** 主要的商業邏輯 *** //

                // 進行每一行的資料檢查或相對應的處理
                System.out.println(readLine); // 示範性的打印每一行
            }
        } catch(IOException e) {
            e.printStackTrace(); // 有出現問題
        }
    }
}
