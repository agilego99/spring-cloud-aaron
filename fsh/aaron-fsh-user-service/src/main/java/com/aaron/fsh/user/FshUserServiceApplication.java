package com.aaron.fsh.user;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.gordianknot.common.base.StartCommand;
/**
 * 房生活-用戶服務-啓動入口
 * @author aaron
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@ComponentScan(basePackages={"com.aaron"})
public class FshUserServiceApplication {
    public static ConfigurableApplicationContext context = null;
    public static void main(String[] args) {
        // 啓動參數設置，比如自動生成端口
        new StartCommand(args);
        // 啓動時初始化配置信息
        System.setProperty("smconf.conf.package", "com.aaron.fsh.user.conf");
        context = SpringApplication.run(FshUserServiceApplication.class, args);
    }
}
