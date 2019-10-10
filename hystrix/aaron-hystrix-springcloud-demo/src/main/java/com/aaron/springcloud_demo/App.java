package com.aaron.springcloud_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * Hystrix 示列
 * 
 * @author aaron
 * 
 * @about 
 * 
 * @date 2019-06-26
 * 
 */
@EnableHystrix
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}