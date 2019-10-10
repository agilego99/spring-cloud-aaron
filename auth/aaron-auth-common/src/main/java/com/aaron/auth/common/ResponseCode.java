package com.aaron.auth.common;
public enum ResponseCode {
 	/** 正確 **/
 	SUCCESS_CODE(200),
 	/** 參數錯誤 **/
 	PARAM_ERROR_CODE(400),
 	/** 限制調用 **/
 	LIMIT_ERROR_CODE(401),
 	/** token 過期 **/
 	TOKEN_TIMEOUT_CODE(402),
 	/** 禁止訪問 **/
 	NO_AUTH_CODE(403),
 	/** 資源沒找到 **/
 	NOT_FOUND(404),
 	/** 服務器錯誤 **/
 	SERVER_ERROR_CODE(500),
 	/** 服務降級中 **/
 	DOWNGRADE(406);
 	private int code;
 	public void setCode(int code) {
 		this.code = code;
 	}
 	public int getCode() {
 		return code;
 	}
 	private ResponseCode(int code) {
 		this.code = code;
 	}
}
