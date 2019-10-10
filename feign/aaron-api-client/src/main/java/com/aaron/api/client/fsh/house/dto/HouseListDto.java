package com.aaron.api.client.fsh.house.dto;

import java.util.List;
import org.gordianknot.common.base.ResponseData;

public class HouseListDto extends ResponseData {
	
	private List<HouseInfo> data;

	public List<HouseInfo> getData() {
		return data;
	}

	public void setData(List<HouseInfo> data) {
		this.data = data;
	}
	
}
