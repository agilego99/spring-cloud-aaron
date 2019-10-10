package org.gordianknot.conf.springboot.demo.conf;

import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.core.SmconfUpdateCallBack;
import org.gordianknot.conf.client.core.rest.Conf;

@GordianknotConf(system="gordianknot-conf-springboot-demo")
public class Biz implements SmconfUpdateCallBack{
	@ConfField("最大值")
	private String max = "920120";

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}
	
	/**
	 * 當配置修改後，若需要執行某些操作；譬如併發高了，則可以根據併發量去調整 Connection Pool 參數。
	 * 在 Smconf Client 可以實現回調接口來監聽修改事件 
	 */
	@Override
	public void reload(Conf conf) {
		// 執行執回調業務邏輯
	}
	
}
