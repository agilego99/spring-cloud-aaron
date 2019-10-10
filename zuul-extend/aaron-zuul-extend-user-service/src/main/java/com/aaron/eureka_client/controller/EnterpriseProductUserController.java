package com.aaron.eureka_client.controller;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aaron.eureka_client.param.LoginParam;
import com.aaron.eureka_client.service.EnterpriseProductUserService;

import io.swagger.annotations.ApiOperation;

import com.aaron.auth.common.ResponseData;

/**
 * 建立用戶認證服務
 * @author Aaron
 */
@RestController
@RequestMapping("/user")
public class EnterpriseProductUserController {
    @Autowired
    private EnterpriseProductUserService enterpriseProductUserService;
    @Autowired
 	private HttpServletRequest request;
   
    /**
     * 用戶登錄
     * @return
     */
    @ApiOperation(value = " 用戶登錄 ", notes = " 企業用戶認證接口，參數為必填欄 ")
    @PostMapping("/login")
    public ResponseData login(@RequestBody LoginParam param) {
        if (param == null || param.getEid() == null || StringUtils.isBlank(param.getUid())) {
            return ResponseData.failByParam("eid 和 uid 不能為空");
        }
        return ResponseData.ok(enterpriseProductUserService.login(param.getEid(), param.getUid()));
    }
   
    @GetMapping("/hello")
    public String hello() {
    	System.err.println("用戶ID:" + request.getHeader("uid"));
    	return "hello";
    }
}
