package org.gordianknot.conf.client.core.rest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.gordianknot.conf.client.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import com.google.common.collect.Lists;
public class ConfRestClient {
 	private static final Logger LOGGER = LoggerFactory.getLogger(ConfRestClient.class);
 	
 	private static ConfRestClient confRestClient = new ConfRestClient();
 	
 	private static RestTemplate restTemplate;
    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        // 添加轉換器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }
   
    public static ConfRestClient getInstance() {
    	return confRestClient;
    }
   
 	public List<Conf> list(String env) {
 		ResponseDatas response = doRequest("/rest/conf/list/{env}", env);
 		return response.getData();
 	}
 	
 	public List<Conf> list(String env, String systemName) {
 		ResponseDatas response = doRequest("/rest/conf/list/{env}/{systemName}", env, systemName);
 		return response.getData();
 	}
 	
 	public List<Conf> list(String env, String systemName, String confFileName) {
 		ResponseDatas response = doRequest("/rest/conf/list/{env}/{systemName}/{confFileName}", env, systemName, confFileName);
 		return response.getData();
 	}
 	
 	public Conf get(String env, String systemName, String confFileName, String key) {
 		ResponseDatas response = doRequest("/rest/conf/list/{env}/{systemName}/{confFileName}/{key}", env, systemName, confFileName, key);
 		List<Conf> list = response.getData();
 		if (list != null && list.size() > 0) {
 			return response.getData().get(0);
 		}
 		return null;
 	}
 	
 	/**
 	* 執行獲取配置數據請求

 	* 首先用註冊中心的服務列表進行請求，全部執行一遍，只要有一個成功即可

 	* 全部執行錯誤後，再回調自己進行重試，休眠1秒
 	* @author aaron
 	* @param url
 	* @param urlVariables
 	* @return
 	*/
 	private ResponseDatas doRequest(String url, Object... urlVariables) {
 		List<String> restApiUrls = getRestApiServers();
 		for (String base : restApiUrls) {
 			try {
 				HttpHeaders requestHeaders = new HttpHeaders();
 			    requestHeaders.add("Authorization", CommonUtil.getRestApiToken());
 			    HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
 			    ResponseDatas resp = restTemplate.exchange(base + url, HttpMethod.GET, requestEntity, ResponseDatas.class, urlVariables).getBody();
 			    if (resp.getStatus()) {
 					return resp;
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 				LOGGER.error(url + " doRequest error", e);
 			}
 		}
 		
 		try {
 			Thread.sleep(1000);
 		} catch (InterruptedException e1) {
 		}
 		return doRequest(url, urlVariables);
 	}
 	static List<String> apis = Lists.newArrayList();
 	/**
 	* 獲取配置API提供的服務地址

 	* @author aaron
 	* @return
 	*/
 	private List<String> getRestApiServers() {
 		List<String> restApiUrls = CommonUtil.getRestServers();
 		if (restApiUrls == null || restApiUrls.size() == 0) {
 			if (apis.size() > 0) {
 				return apis;
 			}
 		}
 		if (restApiUrls.size() == 0) {
 			throw new RuntimeException("找不到配置服務提供者，請先啓動配置管理中心");
 		}
 		
 		List<String> list = Lists.newArrayList();
 		for (String api : restApiUrls) {
 			list.add("http://" + api);
 		}
 		
 		apis = list;
 		return list;
 	}
 	
 	public boolean save(Conf conf) {
 		List<String> restApiUrls = getRestApiServers();
 		for (String base : restApiUrls) {
 			try {
 				HttpHeaders requestHeaders = new HttpHeaders();
 			    requestHeaders.add("Authorization", CommonUtil.getRestApiToken());
 			    HttpEntity<Conf> requestEntity = new HttpEntity<Conf>(conf, requestHeaders);
 			    return restTemplate.exchange(base + "/rest/conf", HttpMethod.POST, requestEntity, ResponseDatas.class).getBody().getStatus();
 			} catch (Exception e) {
 				LOGGER.error(base + " save error", e);
 			}
 		}
 		return false;
 	}
 	
 	public boolean update(Conf conf) {
 		List<String> restApiUrls = getRestApiServers();
 		for (String base : restApiUrls) {
 			try {
 				HttpHeaders requestHeaders = new HttpHeaders();
 			    requestHeaders.add("Authorization", CommonUtil.getRestApiToken());
 			    HttpEntity<Conf> requestEntity = new HttpEntity<Conf>(conf, requestHeaders);
 			    return restTemplate.exchange(base + "/rest/conf/update", HttpMethod.POST, requestEntity, ResponseDatas.class).getBody().getStatus();
 			} catch (Exception e) {
 				LOGGER.error(base + " update error", e);
 			}
 		}
 		return false;
 	}
}
