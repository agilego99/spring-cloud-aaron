package com.aaron.api.client.config;
import feign.Contract;
import feign.Feign;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;
import org.springframework.context.annotation.Scope;
import com.aaron.api.client.config.FeignBasicAuthRequestInterceptor;
@Configuration
public class FeignConfiguration {
    /**
     * 日誌級別
     * @return
     */
    @Bean  
    Logger.Level feignLoggerLevel() {  
        return Logger.Level.FULL;  
    }
    /*@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }*/
   /* @Bean
    public BasicAuthRequestInterceptor myBasicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password");
    }*/
    /**
     * 創建 Feign 請求攔截器，在發送請求前設置認證的 token,各個微服務將 token 設置到環境變量中來達到通用
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
    /*@Bean
    public Decoder decoder() {
     return new MyDecoder();
    }
    @Bean
    public Encoder encoder() {
        return new MyEncoder();
    }*/
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
