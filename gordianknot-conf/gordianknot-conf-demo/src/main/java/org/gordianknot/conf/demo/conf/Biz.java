package org.gordianknot.conf.demo.conf;

import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.core.SmconfUpdateCallBack;
import org.gordianknot.conf.client.core.rest.Conf;

/** @GordianknotConf 用來標示這是一個 Smconf 配置類
 * system 表示當前是哪一個系統在使用
 * env=true 表是當前類下的配置資訊突過 System.setProperty 將值存儲在系統變量中，在程式碼中可以通過 System.getProperty 來獲取，在屬性文件中可以通過 ${key} 來獲取。
 * prefix 為配置類的字串加前綴，若字串名為「max」，prefix「org.gordianknot」則配置的整個 key 就是「org.gordianknot.max」 
 */
@GordianknotConf(system="gordianknot-conf-demo", env=true, prefix="org.gordianknot")
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
