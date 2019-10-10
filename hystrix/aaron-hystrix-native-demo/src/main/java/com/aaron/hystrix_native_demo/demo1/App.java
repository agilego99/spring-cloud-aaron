package com.aaron.hystrix_native_demo.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		String result = new MyHystrixCommand("aaron").execute(); 
		System.out.println("同步調用模式：\t"+result);	
		
		Future<String> future = new MyHystrixCommand("aaron").queue();
		System.out.println("非同步調用模式：\t"+future.get()); 
		context.shutdown();

	}
}
