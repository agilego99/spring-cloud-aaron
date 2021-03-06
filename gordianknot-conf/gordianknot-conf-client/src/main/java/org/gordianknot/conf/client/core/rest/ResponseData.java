package org.gordianknot.conf.client.core.rest;

public class ResponseData {
	private Boolean status = true;
	private int code = 200;
	private String message;
	private Conf data;
	
	public ResponseData(String messgae) {
		this.message = messgae;
	}
	
	public ResponseData() {
		super();
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Conf getData() {
		return data;
	}

	public void setData(Conf data) {
		this.data = data;
	}
	
}
