package com.aaron.eureka_client.feign;

/**
 * API 用戶認證參數類
 * @author aaron
 *
 */
public class AuthQuery {
	private String accessKey;
	
	private String secretKey;
	
	public String getAccessKey() {
		return accessKey;
	}
	
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
	public String getSecretKey() {
		return secretKey;
	}
	
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
