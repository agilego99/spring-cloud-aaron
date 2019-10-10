package com.aaron.hystrix_native_demo.demo2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.aaron.hystrix_native_demo.demo1.MyHystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class CacheApp {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		
		String result = new MyHystrixCommand("aaron").execute();
		System.out.println(result);
		Future<String> future = new MyHystrixCommand("aaron").queue();
		System.out.println(future.get());
		context.shutdown();

	}
}
