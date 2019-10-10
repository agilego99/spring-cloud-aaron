package com.aaron.api.client.fsh.house.dto;

import org.gordianknot.common.base.ResponseData;

/**
 * 房產資訊
 * @author aaron
 **/
public class HouseInfoDto extends ResponseData {
	private HouseInfo data;

	public HouseInfoDto(HouseInfo data) {
		data = this.data;
	}
	
	public HouseInfoDto() {
		
	}

	public HouseInfo getData() {
		return data;
	}

	public void setData(HouseInfo data) {
		this.data = data;
	}
	
}
