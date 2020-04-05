package com.aaron.spring_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author aaron
 * @abount
 * @date 2020-02-17
 * 簡單範例文件
 * @Ref. https://www.jianshu.com/p/305192ea4cb1
 */

@SpringBootApplication
@EnableBatchProcessing
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}