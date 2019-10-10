package com.aaron.job;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

public class MyDataflowJob implements DataflowJob<String> {

	/**
	 * Dataflow 適用於處理資料流，需要實現 Dataflow 介面，該介面提供兩個方法：
	 * 1.抓取資料（fetchData）
	 * 2.處理資料（processData）
	 */
	
	// 抓取資料
	public List<String> fetchData(ShardingContext context) {
		return Arrays.asList("1", "2", "3");
	}

	// 處理資料
	public void processData(ShardingContext context, List<String> data) {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		System.out.println(time +":Dataflow 處理資料：" + data.toString());
	}
}
