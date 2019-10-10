package com.aaron.sjdbc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
/**
 * 分庫又分表示列
 * @author  aaron
 *
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:sharding.xml" })
public class ShardingDbAndTableApplication {
 	public static void main(String[] args) {
 		SpringApplication.run(ShardingDbAndTableApplication.class, args);
 	}
}
