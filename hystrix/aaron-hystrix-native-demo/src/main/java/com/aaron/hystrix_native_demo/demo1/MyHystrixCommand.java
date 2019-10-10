package com.aaron.hystrix_native_demo.demo1;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class MyHystrixCommand extends HystrixCommand<String> {

	private final String name;

	public MyHystrixCommand(String name) {
		//super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
		
		/*super(HystrixCommand.Setter                 
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))                 
				.andCommandPropertiesDefaults(                 		
					HystrixCommandProperties.Setter()             
			    		.withExecutionIsolationStrategy(                	 
			    				HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE 
			                		
			             )                 
			        )        
			    ); */
		
		 super(HystrixCommand.Setter.withGroupKey(
		           HystrixCommandGroupKey.Factory.asKey("MyGroup"))                 
		         .andCommandPropertiesDefaults(     
		             HystrixCommandProperties.Setter()     
		             .withExecutionIsolationStrategy(      
		               HystrixCommandProperties.ExecutionIsolationStrategy.THREAD 
		             )                 
		         ).andThreadPoolPropertiesDefaults(    
		             HystrixThreadPoolProperties.Setter()      
		               .withCoreSize(10)                
		 	       .withMaxQueueSize(100)          
		       	       .withMaximumSize(100)               
		         )         
		);       


		this.name = name;
	}

	@Override
	protected String run() {
		
		// 透過增加執行時間模擬調用超時失敗的情況；測試時可將此註解解除，重新再執行程式一次
		/*
		  try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		System.err.println("get data");   
		return this.name + ":" + Thread.currentThread().getName();
	}

	@Override
	protected String getFallback() {
		return "失敗了";
	}

	@Override     
	protected String getCacheKey() {         
	  return String.valueOf(this.name); 
	}

}
