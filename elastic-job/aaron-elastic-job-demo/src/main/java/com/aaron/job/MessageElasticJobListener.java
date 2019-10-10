package com.aaron.job;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
/**
 * 作業監聽器, 執行前後發送釘釘消息進行通知
 * @author aaron
 */
public class MessageElasticJobListener implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【Gordianknot-" + shardingContexts.getJobName() + "】任務開始執行====" + JsonUtils.toJson(shardingContexts);
        // 釘釘尚未配置
//        DingDingMessageUtil.sendTextMessage(msg);
    }
    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
    	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【Gordianknot-" + shardingContexts.getJobName() + "】任務執行結束====" + JsonUtils.toJson(shardingContexts);
//        釘釘尚未配置
//        DingDingMessageUtil.sendTextMessage(msg);
    }
}
