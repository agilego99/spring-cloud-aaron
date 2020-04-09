package com.aaron.zuul_demo.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.aaron.zuul_demo.filter.LimitFilter;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;
/**
 * 動態管理不需要攔截的 API 請求
 * 並非所有的 API 都需要認證，比如登錄接口。這時可動態添加 API 白名單的功能，凡是在此此白名單當中就不需要認證。
 * 搭配 Apollo 配置管理中心
 * @author Aaron
 */
@Data
@Configuration
public class BasicConf {
 	
 	/** API接口白名單，多個用逗號分隔
 	 *  如不需要認證接口時，直接在 Apollo 配置中心修配置資訊即可即時生效
 	 */
	@Value("${apiWhiteStr:/aaron-zuul-extend-user-service/user/login}")
	private String apiWhiteStr;

    // 單節點限流 Apollo 配置
 	@Value("${limitRate:10}")
 	private double limitRate;
 	
 	// 集群限流 Apollo 配置
 	@Value("${api.clusterLimitRate:10}")
 	private int clusterLimitRate;
 	
 	// 服務降級 Apollo 配置；降級的服務 ID，多個用逗號分隔
 	@Value("${downGradeServiceStr:default}")
 	private String downGradeServiceStr;
 	
 	// 灰度發布 Apollo 配置；根據用戶做灰度發布
 	@Value("${grayPushServers:default}")
 	private String grayPushServers;
 	@Value("${grayPushUsers:default}")
 	private String grayPushUsers;
 	
 	@ApolloConfig
 	private Config config;
 	
 	@ApolloConfigChangeListener
 	public void onChange(ConfigChangeEvent changeEvent) {
 		// limitRate 
 		if (changeEvent.isChanged("limitRate")) {
 			// 更 新 RateLimiter 單節點限流
 			System.err.println("limitRate\t" + config.getDoubleProperty("limitRate", 10.0));
 			LimitFilter.rateLimiter = RateLimiter.create(config.getDoubleProperty("limitRate", 10.0));
 		}

 				
 		// clusterLimitRate 集群限流
 		if (changeEvent.isChanged("api.clusterLimitRate")) {
 			// 更 新 RateLimiter
 			System.err.println("api.clusterLimitRate\t"+config.getDoubleProperty("api.clusterLimitRate", 10.0));
 			LimitFilter.rateLimiter = RateLimiter.create(config.getDoubleProperty("api.clusterLimitRate", 10.0));
 		}
 	}
}
