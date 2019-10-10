package com.aaron.auth.controller;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aaron.auth.common.ResponseData;
import com.aaron.auth.po.User;
import com.aaron.auth.query.AuthQuery;
import com.aaron.auth.service.AuthService;
import com.aaron.auth.util.JWTUtils;
@RestController
@RequestMapping(value="/oauth")
public class AuthController {
 	
 	@Autowired
 	private AuthService authService;
 	
 	/**
 	 * 認證接口；用於調用方式將行認證時，認證通過則返回一個加密的 Token 給對方，對方就可以用這個 Token 去請求別的服務。
 	 * Post 方法
 	 * @param query
 	 * @return
 	 * @throws Exception
 	 */
 	@PostMapping("/token")
 	public ResponseData auth(@RequestBody AuthQuery query) throws Exception {
 		if (StringUtils.isBlank(query.getAccessKey()) || StringUtils.isBlank(query.getSecretKey())) {
 			return ResponseData.failByParam("accessKey and secretKey not null");
 		}
 		
 		User user = authService.auth(query);
 		if (user == null) {
 			return ResponseData.failByParam("認證失敗");
 		}
 		
 		
 		JWTUtils jwt = JWTUtils.getInstance();
 		return ResponseData.ok(jwt.getToken(user.getId().toString()));
 	}
 	
 	/**
 	 * 認證接口；用於調用方式將行認證時，認證通過則返回一個加密的 Token 給對方，對方就可以用這個 Token 去請求別的服務。
 	 * Get 方法
 	 * @param query
 	 * @return
 	 * @throws Exception
 	 */
 	@GetMapping("/token")
 	public ResponseData oauth(AuthQuery query) throws Exception {
 		if (StringUtils.isBlank(query.getAccessKey()) || StringUtils.isBlank(query.getSecretKey())) {
 			return ResponseData.failByParam("accessKey and secretKey not null");
 		}
 		User user = authService.auth(query);
 		if (user == null) {
 			return ResponseData.failByParam("認證失敗");
 		}
 		JWTUtils jwt = JWTUtils.getInstance();
 		return ResponseData.ok(jwt.getToken(user.getId().toString()));
 	} 	
}
