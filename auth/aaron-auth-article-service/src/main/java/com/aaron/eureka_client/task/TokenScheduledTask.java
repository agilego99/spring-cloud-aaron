package com.aaron.eureka_client.task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.aaron.auth.common.ResponseData;
import com.aaron.eureka_client.feign.AuthQuery;
import com.aaron.eureka_client.feign.AuthRemoteClient;
/**
 * 定時刷新token
 *
 * @author aaron
 *
 **/
@Component
public class TokenScheduledTask {
 	private static Logger logger = LoggerFactory.getLogger(TokenScheduledTask.class);
 	// 若 Token 有效期為 24h，則可以間隔 20h 刷新一次來確保調用的正確性
 	public final static long ONE_Minute = 60 * 1000 * 60 * 20;
 	@Autowired
 	private AuthRemoteClient authRemoteClient;
 	/**
 	* 刷新Token
 	*/
 	@Scheduled(fixedDelay = ONE_Minute)
 	public void reloadApiToken() {
 		String token = this.getToken();
 		while (StringUtils.isBlank(token)) {
 			try {
 				Thread.sleep(1000);
 				token = this.getToken();
 			} catch (InterruptedException e) {
 				logger.error("", e);
 			}
 		}
 		System.setProperty("fangjia.auth.token", token);
 		logger.info("申請token成功 :{}", token);
 	}
 	public String getToken() {
 		AuthQuery query = new AuthQuery();
 		query.setAccessKey("1");
 		query.setSecretKey("1");
 		ResponseData response = authRemoteClient.auth(query);
 		System.err.println("透過 TokenScheduledTask 取得 Token");
 		return response.getData() == null ? "" : response.getData().toString();
 	}
}
