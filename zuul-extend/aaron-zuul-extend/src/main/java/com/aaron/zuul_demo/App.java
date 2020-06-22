package com.aaron.zuul_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul 擴展實例
 *
 * @author aaron
 * @date 2019 -08-26
 */
@EnableZuulProxy
@SpringBootApplication
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		// 指定環境（開發演示用，不能用於生產環境））
		System.setProperty("env", "DEV");
		SpringApplication.run(App.class, args);
	}
}