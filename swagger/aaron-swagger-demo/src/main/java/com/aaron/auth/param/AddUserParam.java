package com.aaron.auth.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// API Data Model
@Data
/**
 * @ApiModel 用在類，表示對類進行說明，用於實體類中的參數接收說明。
 */
@ApiModel(value = "com.aaron.auth.param.AddUserParam", description = "新增用户参数") 
public class AddUserParam {
	
	/**
	 * @ApiModelProperty 對於 model屬性的說明
	 */
	@ApiModelProperty(value="ID")
	private String id;
	
	@ApiModelProperty(value="名稱")
	private String name;
	
	@ApiModelProperty(value="年龄")
	private int age;
}
