package com.aaron.apollo_java;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
/**
* Apollo 整合 Java 專案示列
*
* @author aaron
*
* @about 
*
* @date 2019-07-01
*
*/
public class App {
    public static void main(String[] args) {
        // 通過 ConfigService 得到 Config 物件
    	Config config = ConfigService.getAppConfig();
        // 指定環境
        System.setProperty("env", "DEV");
        getValue(config);
        addChangeListener(config);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static void addChangeListener(Config config) {
        config.addChangeListener(new ConfigChangeListener() {
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("發生修改數據的命名空間是：" + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("發現修改 - 配置key: %s, 原來的值: %s, 修改後的值: %s, 操作類型: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });
    }
    
    private static void getValue(Config config) {
        String key = "username";
        String defaultValue = "朱郭隆";
        // key 一律小寫
        String username = config.getProperty(key, defaultValue);
        System.out.println("username=" + username);
    }
}
