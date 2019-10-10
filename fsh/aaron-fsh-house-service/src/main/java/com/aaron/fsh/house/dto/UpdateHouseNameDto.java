package com.aaron.fsh.house.dto;
/**
 * 小區名稱修改資料傳輸資訊
 * @author aaron
 */
public class UpdateHouseNameDto {
 	// 城市
 	private String city;
 	// 區域
 	private String region;
 	// 小區名稱
 	private String name;
 	// 新的小區名稱
 	private String newName;
 	
 	public UpdateHouseNameDto(String city, String region, String name, String newName) {
 		super();
 		this.city = city;
 		this.region = region;
 		this.name = name;
 		this.newName = newName;
 	}
 	public String getCity() {
 		return city;
 	}
 	public void setCity(String city) {
 		this.city = city;
 	}
 	public String getRegion() {
 		return region;
 	}
 	public void setRegion(String region) {
 		this.region = region;
 	}
 	public String getName() {
 		return name;
 	}
 	public void setName(String name) {
 		this.name = name;
 	}
 	public String getNewName() {
 		return newName;
 	}
 	public void setNewName(String newName) {
 		this.newName = newName;
 	}
 	
}
