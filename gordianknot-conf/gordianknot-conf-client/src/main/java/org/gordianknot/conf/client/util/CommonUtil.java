package org.gordianknot.conf.client.util;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.gordianknot.conf.client.common.Constant;
import org.gordianknot.conf.client.common.EnvConstants;
import org.gordianknot.conf.client.common.JsonUtils;
import org.gordianknot.conf.client.core.rest.ConfRestClient;
import org.gordianknot.conf.client.zk.ZkClient;
import org.springframework.util.StringUtils;
public class CommonUtil {
 	
 	/**
 	* 獲取配置信息Rest Client
 	* @author aaron
 	* @return
 	*/
 	public static ConfRestClient getConfRestClient() {
        ConfRestClient confRestClient = ConfRestClient.getInstance();
        return confRestClient;
 	}
 	
 	/**
 	* 獲取所有提供Rest API服務的地址
 	* @author aaron
 	* @return
 	*/
 	public static List<String> getRestServers() {
 		return getZkClient().getAllServer();
 	}
 	
 	/**
 	* 獲取zookeeper Client
 	* @author aaron
 	* @return
 	*/
 	public static ZkClient getZkClient() {
 		String zkUrl = System.getProperty(Constant.ZK_URL);
 		if (zkUrl != null) {
 			return ZkClient.getInstance(zkUrl);
 		}
 		
 		zkUrl = ProUtils.getProperty(Constant.ZK_URL);
 		if (zkUrl == null) {
 			throw new RuntimeException("請在application.properties中配置zookeeper.url");
 		}
 		return ZkClient.getInstance(zkUrl);
 	}
 	
 	/**
 	* 獲取本服務的環境
 	* @author aaron
 	* @return
 	*/
 	public static String getEnv() {
 		String systemProperty = System.getProperty(Constant.PROFILE_ACTIVE);
 		if (StringUtils.hasText(systemProperty)) {
 			return systemProperty;
 		}
 		
 		String env = ProUtils.getProperty(Constant.PROFILE_ACTIVE);
 		if (!StringUtils.hasText(env)) {
 			env = EnvConstants.DEV;
 		}
 		
 		if (!EnvConstants.ENVS.contains(env)) {
 			throw new RuntimeException(Constant.PROFILE_ACTIVE + "可選值只有：" + EnvConstants.ENVS.toString());
 		}
 		
 		return env;
 	}
 	
 	/**
 	* 獲取本服務的端口
 	* @author aaron
 	* @return
 	*/
 	public static String getServerPort() {
 		String port = System.getProperty(Constant.SERVER_PORT);
 		if (StringUtils.hasText(port)) {
 			return port;
 		}
 		port = ProUtils.getProperty(Constant.SERVER_PORT);
 		if (!StringUtils.hasText(port)) {
 			throw new RuntimeException("請配置" + Constant.SERVER_PORT + "用來區分一台機器上的多個服務");
 		}
 		//如果屬性文件中配置的值是${spring.port}
 		if (port.startsWith("$")) {
 			port = System.getProperty(port.substring(2, port.length() - 1));
 		}
 		return port;
 	}
 	
 	/**
 	* 獲取本機IP
 	* @author aaron
 	* @return
 	*/
 	public static String getLocalIp() {
 		String ip = System.getProperty(Constant.SERVER_IP);
 		if (StringUtils.hasText(ip)) {
 			return ip;
 		}
 		ip = ProUtils.getProperty(Constant.SERVER_IP);
 		if (StringUtils.hasText(ip)) {
 			return ip;
 		}
 		String localIp = NetUtils.getLocalAddress().getHostAddress();
 		return localIp;
 	}
 	/**
 	* 獲取是否覆蓋配置中心的配置狀態
 	* @return
 	*/
 	public static boolean getConfOverwrite() {
 		String status = System.getProperty(Constant.OVERWRITE_STATUS);
 		if (StringUtils.hasText(status)) {
 			return Boolean.parseBoolean(status);
 		}
 		status = ProUtils.getProperty(Constant.OVERWRITE_STATUS);
 		if (StringUtils.hasText(status)) {
 			return Boolean.parseBoolean(status);
 		}
 		return false;
 	}
 	
 	/**
 	* 拼接路徑
 	* @author aaron
 	* @param objects
 	* @return
 	*/
 	public static String buildPath(Object...objects) {
 		StringBuilder sb = new StringBuilder();
 		for (Object obj : objects) {
 			sb.append(obj);
 			sb.append("/");
 		}
 		sb.delete(sb.length() - 1, sb.length());
 		return sb.toString();
 	}
 	
 	/**
 	* 將值設置到Field中
 	* @author aaron
 	* @param field
 	* @param bean
 	* @param value
 	* @throws Exception
 	*/
 	public static void setValue(Field field, Object bean, Object value) throws Exception {
 		if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
 			field.set(bean, Integer.parseInt(value.toString()));
 		} else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
 			field.set(bean, Long.parseLong(value.toString()));
 		} else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
 			field.set(bean, Double.parseDouble(value.toString()));
 		} else if(field.getType().getName().equals("java.util.Map")) {
 			field.set(bean, JsonUtils.toBean(Map.class, value.toString().replaceAll("'", "\"")));
 		} else {
 			field.set(bean, value);
 		}
 	}
 	
 	/**
 	* 獲取rest api token
 	* @author aaron
 	* @return
 	*/
 	public static String getRestApiToken() {
 		String token = System.getProperty(Constant.REST_API_TOKEN);
 		if (StringUtils.hasText(token)) {
 			return token;
 		}
 		
 		token = ProUtils.getProperty(Constant.REST_API_TOKEN);
 		if (StringUtils.hasText(token)) {
 			return token;
 		}
 		
 		return "58eef205c24381110802b011";
 	}
 	
 	/**
 	* 獲取本地配置數據狀態（local只加載本地默認配置  remote加載配置中心配置）
 	* @author aaron
 	* @return
 	*/
 	public static String getLocalDataStatus() {
 		String status = System.getProperty(Constant.DATA_STATUS);
 		if (StringUtils.hasText(status)) {
 			return status;
 		}
 		status = ProUtils.getProperty(Constant.DATA_STATUS);
 		if (StringUtils.hasText(status)) {
 			return status;
 		}
 		return "remote";
 	}
 	
 	/**
 	* 獲取配置所在包路徑，多個用逗號隔開
 	* @author aaron
 	* @return
 	*/
 	public static String getConfPackagePaths() {
 		String value = System.getProperty(Constant.CONF_PACKAGES);
 		if (StringUtils.hasText(value)) {
 			return value;
 		}
 		
 		value = ProUtils.getProperty(Constant.CONF_PACKAGES);
 		if (StringUtils.hasText(value)) {
 			return value;
 		}
 		
 		return "org.gordianknot";
 	}
 	
}
