package com.aaron.hystrix_native_demo.demo2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class CacheCleanApp {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		String result = new ClearCacheHystrixCommand("aaron").execute();
		System.out.println(result);
		ClearCacheHystrixCommand.flushCache("aaron");
		Future<String> future = new ClearCacheHystrixCommand("aaron").queue();
		System.out.println(future.get());
		context.shutdown();

	}
}
