package com.aaron.transaction_mq.query;

import org.gordianknot.jdbc.PageQueryParam;

public class MessageQuery extends PageQueryParam {

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
