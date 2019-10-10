package com.aaron.feign_demo;


/**
 * Feign原生操作示列
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-01-01
 * 
 */
public class App {
	public static void main(String[] args) {
		HelloRemote helloRemote = RestApiCallUtils.getRestClient(HelloRemote.class,"http://localhost:8083");
		
		System.out.println(" 調用結果："+helloRemote.hello());
	}
}