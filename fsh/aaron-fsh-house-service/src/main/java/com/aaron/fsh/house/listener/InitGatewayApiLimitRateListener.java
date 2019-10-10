package com.aaron.fsh.house.listener;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.gordianknot.conf.client.init.ConfInit;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.gordianknot.common.anno.ApiRateLimit;
import org.gordianknot.common.util.ClasspathPackageScannerUtils;
import com.aaron.fsh.house.conf.RateLimitConf;
/**
 * 初始化 API Gateway 需要進行開發限制的 API
 * @author aaron
 */
public class InitGatewayApiLimitRateListener implements ApplicationListener<ApplicationReadyEvent> {
 	// Controller Package 路徑
 	private String controllerPath;
 	private RateLimitConf rateLimitConf;
 	
 	private ConfInit confInit;
 	
 	private String applicationName;
 	
 	public InitGatewayApiLimitRateListener(String controllerPath) {
 		this.controllerPath = controllerPath;
 	}
 	@Override
 	public void onApplicationEvent(ApplicationReadyEvent event) {
 		this.rateLimitConf = event.getApplicationContext().getBean(RateLimitConf.class);
 		this.confInit = event.getApplicationContext().getBean(ConfInit.class);
 		this.applicationName = event.getApplicationContext().getEnvironment().getProperty("spring.application.name");
 		try {
 			initLimitRateAPI();
 		} catch (Exception e) {
 			throw new RuntimeException("初始化需要進行併發限制的API異常", e);
 		}
 	}
 	
 	/**
 	* 初始化需要進行併發限制的API
 	* @throws IOException
 	* @throws ClassNotFoundException
 	*/
 	private void initLimitRateAPI() throws IOException, ClassNotFoundException {
 		Map<String, Integer> limitMap = rateLimitConf.getLimitMap();
 		ClasspathPackageScannerUtils scan = new ClasspathPackageScannerUtils(this.controllerPath);
 		List<String> classList = scan.getFullyQualifiedClassNameList();
 		for (String clazz : classList) {
 			Class<?> clz = Class.forName(clazz);
 			if (!clz.isAnnotationPresent(RestController.class)) {
 				continue;
 			}
 			Method[] methods = clz.getDeclaredMethods();
 			for (Method method : methods) {
 				if (method.isAnnotationPresent(ApiRateLimit.class)) {
 					ApiRateLimit apiRateLimit = method.getAnnotation(ApiRateLimit.class);
 					String replenishRateKey = applicationName + "." + getApiUri(clz, method) + ".replenishRate";
 					String burstCapacityKey = applicationName + "." + getApiUri(clz, method) + ".burstCapacity";
 					limitMap.put(replenishRateKey, apiRateLimit.replenishRate());
 					limitMap.put(burstCapacityKey, apiRateLimit.burstCapacity());
 				}
 			}
 		}
 		rateLimitConf.setLimitMap(limitMap);
 		// 初始化值到配置中心
 		confInit.init(rateLimitConf);
 	}
 	private String getApiUri(Class<?> clz, Method method) {
 	        StringBuilder uri = new StringBuilder();
 	        uri.append(clz.getAnnotation(RequestMapping.class).value()[0]);
 	        if (method.isAnnotationPresent(GetMapping.class)) {
 	            uri.append(method.getAnnotation(GetMapping.class).value()[0]);
 	        } else if (method.isAnnotationPresent(PostMapping.class)) {
 	            uri.append(method.getAnnotation(PostMapping.class).value()[0]);
 	        } else if (method.isAnnotationPresent(RequestMapping.class)) {
 	            uri.append(method.getAnnotation(RequestMapping.class).value()[0]);
 	        }
 	        return uri.toString();
 	}
}
