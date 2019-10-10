package com.aaron.job;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
/**
 * 實現 Simple 介面
 * 分片處理
 * execute 方法內實現業務邏輯
 */
public class MySimpleShardingJob implements SimpleJob {
	
	/**
	 * 模擬 100萬條資料
	 */
 	public void execute(ShardingContext context) {
 		// 獲取當前節點分片參數
 		String shardParamter = context.getShardingParameter();
 		System.out.println("分片參數："+shardParamter);
 		int value = Integer.parseInt(shardParamter);
 		for (int i = 0; i < 1000000; i++) {
 			// 通過取模的方式跟分片的參數對比，對上了就處理這條資料
 			if (i % 2 == value) {
 				String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
 				System.out.println(time + ":開始執行簡單任務" + i);
 			}
 		}
 	}
}