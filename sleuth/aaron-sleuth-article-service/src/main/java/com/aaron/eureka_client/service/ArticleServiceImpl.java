package com.aaron.eureka_client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import brave.Tracer;

@Service
public class ArticleServiceImpl implements ArticleService {

	private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	Tracer tracer;

	@Async
	@Override
	public void saveLog(String log) {
		logger.info("異步線程執行");

	}

	/**
	 * 異步執行和遠程調用都會新開啟一個 Span，如果要監控本地方法所耗費的時間，可以採用埋點的方式監控本地方法；
	 * 也就是開啟一個新的 Span
	 * 手動埋點
	 */
	@NewSpan(name = "saveLog2")
	@Override
	public void saveLog2(String log) {
		//ScopedSpan span = tracer.startScopedSpan("saveLog2");
		try {
			Thread.sleep(2000);
		} catch (Exception | Error e) {
			//span.error(e);
		} finally {
			//span.finish(); 
		}
	}
}
