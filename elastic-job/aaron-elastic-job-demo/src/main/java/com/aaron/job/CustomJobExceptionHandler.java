package com.aaron.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;

/**
 * 自定義異常處理，在任務異常時使用叮叮發送通知
 * @author aaron
 */
public class CustomJobExceptionHandler implements JobExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(CustomJobExceptionHandler.class);

    @Override
    public void handleException(String jobName, Throwable cause) {
        logger.error(String.format("Job '%s' exception occur in job processing", jobName), cause);
        // 釘釘尚未配置
//        DingDingMessageUtil.sendTextMessage("【"+jobName+"】任務異常。" + cause.getMessage());
    }
}