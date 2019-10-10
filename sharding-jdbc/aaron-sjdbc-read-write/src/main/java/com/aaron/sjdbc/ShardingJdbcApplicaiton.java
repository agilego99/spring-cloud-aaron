package com.aaron.sjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 讀寫分離示列
 * @author aaron
 *
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:sharding.xml" })
public class ShardingJdbcApplicaiton {
	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcApplicaiton.class, args);
	}
}
