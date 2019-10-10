package com.aaron.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "com.aaron.auth.dto.UserDto", description = "用户信息") 
public class UserDto {

	@ApiModelProperty(value="ID")
	private String id;
	
	@ApiModelProperty(value="名稱")
	private String name;
	
}
