package com.aaron.job;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author Aaron
 * 配置檔：MySample.xml
 * 如啟動出現異常：
 * 1.需要使用 Zookeeper 客戶端連接到 ZK 服務器（透過 Zooinspector），刪除 Elastic-Job 在 Zookeeper 註冊的節點
 * 2.重新啓動 Elastic-Job
 */
public class MySimpleJob implements SimpleJob {
 	public void execute(ShardingContext context) {
 		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
 		System.out.println(time + ":開始執行簡單任務");
 	}
}