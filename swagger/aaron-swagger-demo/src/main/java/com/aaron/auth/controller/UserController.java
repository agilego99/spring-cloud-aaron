package com.aaron.auth.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aaron.auth.dto.UserDto;
import com.aaron.auth.param.AddUserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 *  @Api 說名該類的作用；可以標記一個 Controller 類作為 Swagger 文檔資源。
 *  tags 接口說明，可以在頁面中縣使，可以配置多個。
 */
@Api(tags={"用戶接口"})
@RestController
public class UserController {
    @ApiOperation(value = "查詢用戶")
    @ApiImplicitParams({
          @ApiImplicitParam(name="id",value="用戶ID",dataType="string", paramType = "query", required=true, defaultValue="1")
    })
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserDto.class) })
    @GetMapping("/user")
    public UserDto getUser(@RequestParam("id")String id) {
        return new UserDto();
    }
    
    @ApiOperation(value = "新增用戶", notes="詳細描述")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = UserDto.class) })
    @PostMapping("/user")
    public UserDto addUser(@ApiParam(value = " 新增用戶參數 ", required = true) @RequestBody AddUserParam param) {
        System.err.println(param.getName());
        return new UserDto();
    }
}
