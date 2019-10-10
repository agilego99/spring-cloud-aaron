package org.gordianknot.conf.demo;
import org.gordianknot.conf.client.ConfApplication;
import org.gordianknot.conf.demo.conf.Biz;
import org.gordianknot.conf.demo.conf.DbConf;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 配置測試類
 * @author aaron
 */
public class App {
	// 抑制與使用 Closeable 類型的資源相關的警告
 	@SuppressWarnings("resource")
 	public static void main(String[] args) {
 		
 		//可以通過環境變量設置 zk 和 env 的值
 		//System.setProperty("zookeeper.url", "gordianknot:2181");
 		//System.setProperty("spring.profiles.active", "test");
 		 		
 		new ClassPathXmlApplicationContext("spring.xml").start();
 		System.out.println(ConfApplication.getBean(DbConf.class).getMaxTime());
 		
 		//測試有前綴的並且要設置到環境變量中的值
 		System.out.println(System.getProperty("org.gordianknot.max"));
 		
 		Biz biz = ConfApplication.getBean(Biz.class);
 		String max = biz.getMax();
 		System.out.println(max);
 		try {
 			Thread.sleep(1000 * 60);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
 		String max2 = biz.getMax();
 		System.out.println(max2);
 	}
}
 
