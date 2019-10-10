package org.gordianknot.conf.demo;
import org.gordianknot.conf.client.ConfApplication;
import org.gordianknot.conf.client.init.SmconfInit;
import org.gordianknot.conf.demo.conf.DbConf;
/**
* 
* @author aaron
*
*/
public class NoSpringEnvDemo {
    public static void main(String[] args) {
    	/* 
    	  非 Spring環境中使用： 
          手動調用初始化的方法；在還沒初始化之前加載配置資訊，可以在啟動類中通過設置全局
          以本專案為例會以 org.gordianknot.conf.demo.conf 下的 Biz 及 DbConf 的內容為初始化
          需不需要初始化取決於是否要在初始化之前使用配置資訊，如果不需要就不用配置，比如資料庫連線資訊在啟動時就需要。
        */
    	SmconfInit.init("org.gordianknot.conf.demo.conf");
        System.out.println(ConfApplication.getBean(DbConf.class).getMaxTime());
    }
}
