package org.gordianknot.conf.client.init;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.util.ClassReadUtils;
import org.gordianknot.conf.client.util.ClasspathPackageScannerUtils;
import org.gordianknot.conf.client.util.CommonUtil;
import org.springframework.util.StringUtils;
/**
 * 啓動時需要配置來做連接，需要在spring啓動前將一些配置信息加載到環境變量使用,多個包用逗號隔開

 * 可以不傳參數，默認讀取com包下所有的類信息
 * @author aaron
 *
 */
public class SmconfInit {
 	public static void init(String basePackgaes) {
 		ConfInit confInit = new ConfInit();
 		Map<String, Object> beanMap = new HashMap<String, Object>();
 		try {
 			if (!StringUtils.hasText(basePackgaes)) {
 				basePackgaes = "org.gordianknot";
 			}
 			String[] packs = basePackgaes.split(",");
 			for (String pck : packs) {
 				Set<Class<?>> classList = ClassReadUtils.getClassFromPackagePath(pck);
 				for (Class<?> clz : classList) {
 		        	if (clz.isAnnotationPresent(GordianknotConf.class)) {
 		        		beanMap.put(clz.getName(), clz.newInstance());
 		        	}
 				}
 				
 			}
 			confInit.check(beanMap);
 			confInit.init(beanMap, true, CommonUtil.getConfOverwrite());
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}
 		/*ClasspathPackageScannerUtils scan = new ClasspathPackageScannerUtils(basePackgae);
        try {
 			List<String> classList = scan.getFullyQualifiedClassNameList();
 			for (String clazz : classList) {
 				Class<?> clz = Class.forName(clazz);
 	        	if (clz.isAnnotationPresent(GordianknotConf.class)) {
 	        		beanMap.put(clazz, clz.newInstance());
 	        	}
 			}
 			
 			confInit.check(beanMap);
 			confInit.init(beanMap, true);
 		} catch (Exception e) {
 			throw new RuntimeException(e);
 		}*/
 		
 	}
}
