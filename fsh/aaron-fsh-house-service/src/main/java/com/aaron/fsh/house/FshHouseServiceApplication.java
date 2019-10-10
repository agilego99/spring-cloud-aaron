package com.aaron.fsh.house;
import org.gordianknot.common.base.StartCommand;
import org.gordianknot.common.listenter.InitApiLimitRateListener;
import com.aaron.fsh.house.listener.InitGatewayApiLimitRateListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
/**
 * 房生活-房產服務-啓動入口
 * @author aaron
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@ComponentScan(basePackages={"com.aaron"})
//@EnableFeignClients(basePackages = "com.aaron.mqclient")
public class FshHouseServiceApplication {
    public static ConfigurableApplicationContext context = null;
    public static void main(String[] args) {
        // 啓動參數設置，比如自動生成端口
        //new StartCommand(args);
        // 啓動時初始化配置信息
    	System.setProperty("smconf.conf.package", "com.aaron.fsh.house.conf");
        SpringApplication application = new SpringApplication(FshHouseServiceApplication.class);
        //application.addListeners(new InitApiLimitRateListener("com.aaron.fsh.house.controller"));
        application.addListeners(new InitGatewayApiLimitRateListener("com.aaron.fsh.house.controller"));
        context = application.run(args);
    }
}
