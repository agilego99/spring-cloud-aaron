package org.gordianknot.conf.client.core;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.gordianknot.conf.client.ConfApplication;
import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.core.rest.Conf;
import org.gordianknot.conf.client.core.rest.ConfRestClient;
import org.gordianknot.conf.client.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RefreshConfCallBackImpl implements RefreshConfCallBack {
 	private static final Logger LOGGER = LoggerFactory.getLogger(RefreshConfCallBackImpl.class);
 	
 	public void call(String path) {
 		if (CommonUtil.getLocalDataStatus().equals("local")) {
 			return;
 		}
 		LOGGER.info(path + " update...");
 		String[] values = path.split("/");
 		String env = values[2];
 		String system = values[3];
 		String confFileName = values[4].split("&")[1];
 		Object confBean = ConfApplication.getBean(env, system, confFileName);
 		if (confBean == null) {
 			return;
 		}
 		
 		GordianknotConf gordianknotConf = confBean.getClass().getAnnotation(GordianknotConf.class);
    	boolean isEnv = gordianknotConf.env();
    	String prefix = gordianknotConf.prefix();
 		Field[] fieds = confBean.getClass().getDeclaredFields();
 		
 		ConfRestClient restClient = CommonUtil.getConfRestClient();
 		List<Conf> confs = restClient.list(env, system, confFileName);
 		for (Conf conf : confs) {
        	for (Field field : fieds) {
        		try {
        			field.setAccessible(true);
    				if (field.getName().equals(conf.getKey()) && (!field.get(confBean).toString().equals(conf.getValue()))) {
    					LOGGER.info(field.getName() + "\tOldVlaue:" + field.get(confBean) + "\tNewValue:" + conf.getValue());
    					CommonUtil.setValue(field, confBean, conf.getValue());
    					if (isEnv) {
    						System.setProperty(prefix.equals("") ? conf.getKey() : prefix + "." + conf.getKey(), conf.getValue().toString());
    					}
    					//設置回調用戶自定義方法
    					setCallBackMethod(confBean, conf);
    					break;
    				}
 				} catch (Exception e) {
 					LOGGER.error("", e);
 				}
 			}
 		}
 	}
 	private void setCallBackMethod(Object confBean, Conf conf) {
 		Class<?>[] inters = confBean.getClass().getInterfaces();
 		if (inters != null && inters.length > 0) {
 			for (Class<?> clz : inters) {
 				if (clz.getSimpleName().equals("SmconfUpdateCallBack")) {
 				    try {
 						Method m1 = confBean.getClass().getDeclaredMethod("reload", Conf.class);
 						m1.invoke(confBean, conf);
 					} catch (Exception e) {
 						LOGGER.error("設置回調用戶自定義方法異常", e);
 					}
 					break;
 				}
 			}
 		}
 	}
}
