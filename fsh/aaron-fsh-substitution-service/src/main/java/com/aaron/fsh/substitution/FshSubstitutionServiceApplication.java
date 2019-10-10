package com.aaron.fsh.substitution;
import org.gordianknot.common.base.StartCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
/**
 * 房生活-置換服務-啓動入口
 * @author aaron
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"com.aaron.api.client", "com.aaron.mqclient"})
@ComponentScan(basePackages = "com.aaron")
public class FshSubstitutionServiceApplication {
 	
    public static void main(String[] args) {
        // 啓動參數設置，比如自動生成端口
        new StartCommand(args);
        // 啓動時初始化配置信息
        System.setProperty("smconf.conf.package", "com.aaron.fsh.substitution.conf");
        SpringApplication.run(FshSubstitutionServiceApplication.class, args);
    }
}
