package com.aaron.fsh.user.controller;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.gordianknot.common.base.ResponseData;
import com.aaron.fsh.user.query.LoginQuery;
import com.aaron.fsh.user.service.EnterpriseProductUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
/**
 * 企業用戶控制器
 * @author aaron
 **/
//@Api(value="企業用戶控制器", tags={"用戶接口", "企業用戶接口"})
@Api(value="企業用戶控制器", tags={"用戶接口"})
@RestController
@RequestMapping("/user")
public class EnterpriseProductUserController {
    @Autowired
    private EnterpriseProductUserService enterpriseProductUserService;
    /**
     * 用戶登錄
     * @param query
     * @return
     */
    @ApiOperation(value = "用戶登錄", notes = "企業用戶認證接口，參數為必填項")
    @PostMapping("/login")
    @ApiResponses({ @ApiResponse(code = 403, message = "無權限訪問") })
    @ResponseHeader(name="myhead",description="aaron")
    public ResponseData login(@ApiParam(value = "登錄參數", required = true) @RequestBody LoginQuery query) {
        if (query == null || query.getEid() == null || StringUtils.isBlank(query.getUid())) {
            return ResponseData.failByParam("eid和uid不能為空");
        }
        return ResponseData.ok(enterpriseProductUserService.login(query.getEid(), query.getUid()));
    }
   
    @ApiImplicitParams({
    	@ApiImplicitParam(name="uid", value="用戶ID", required=true, paramType="query", dataType="String", defaultValue="1")
    })
    @GetMapping("/hello")
    public String hello(String uid) {
    	return uid;
    }
}
