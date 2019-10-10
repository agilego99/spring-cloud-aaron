package com.aaron.eureka_client.service;

import org.springframework.stereotype.Service;

import com.aaron.auth.util.JWTUtils;

@Service
public class EnterpriseProductUserServiceImpl implements EnterpriseProductUserService {

	/**
	 * 判斷是否成功登錄，成功則用 JWT 將用戶的 ID 加密返回一組 Token；在此為模擬，實務上需要從資料庫取得資料。
	 * 認證邏輯
	 */
    @Override
    public String login(Long eid, String uid) {
        JWTUtils jwtUtils = JWTUtils.getInstance();
        if (eid.equals(1L) && uid.equals("1001")) {
            return jwtUtils.getToken(uid);
        }
        return null;
    }
}
