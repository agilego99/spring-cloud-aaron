package com.aaron.fsh.house.conf;
import java.util.concurrent.Semaphore;
import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.core.SmconfUpdateCallBack;
import org.gordianknot.conf.client.core.rest.Conf;
import org.gordianknot.common.aspect.ApiLimitAspect;
/**
 * 接口限流配置
 * @author aaron
 *
 */
@GordianknotConf(system = "aaron-fsh-house-service", env = true, prefix = "open.api")
public class RestAPIRateLimitConf implements SmconfUpdateCallBack {
 	@ConfField("默認的接口併發限制")
 	private int defaultLimit = 10;
 	@ConfField("房產信息接口")
 	private int hosueInfo = 1;
 	public void setHosueInfo(int hosueInfo) {
 		this.hosueInfo = hosueInfo;
 	}
 	public int getHosueInfo() {
 		return hosueInfo;
 	}
 	public int getDefaultLimit() {
 		return defaultLimit;
 	}
 	public void setDefaultLimit(int defaultLimit) {
 		this.defaultLimit = defaultLimit;
 	}
 	@Override
 	public void reload(Conf conf) {
 		ApiLimitAspect.semaphoreMap.put("open.api." + conf.getKey(), new Semaphore(Integer.parseInt(conf.getValue().toString())));
 	}
}
