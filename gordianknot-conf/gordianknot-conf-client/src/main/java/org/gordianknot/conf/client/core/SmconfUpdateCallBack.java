package org.gordianknot.conf.client.core;
import org.gordianknot.conf.client.core.rest.Conf;
/**
 * 用戶可以在配置文件中實現該接口，對接口修改變動做一些自定義的處理
 * 該方法被觸發的時候，修改的值已經被加載到了對應的bean對象中
 * @author aaron
 *
 */
public interface SmconfUpdateCallBack {
 	
 	public void reload(Conf conf);
 	
}
