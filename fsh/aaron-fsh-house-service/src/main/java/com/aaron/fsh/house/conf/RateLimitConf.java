package com.aaron.fsh.house.conf;

import java.util.HashMap;
import java.util.Map;

import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;

@GordianknotConf(system="aaron-gateway")
public class RateLimitConf {
	// 限流配置
	@ConfField(value = "limitMap")
	private Map<String, Integer> limitMap = new HashMap<String, Integer>(){{
		put("default.replenishRate", 100);
		put("default.burstCapacity", 1000);
	}};
	public void setLimitMap(Map<String, Integer> limitMap) {
		this.limitMap = limitMap;
	}
	public Map<String, Integer> getLimitMap() {
		return limitMap;
	}
}
