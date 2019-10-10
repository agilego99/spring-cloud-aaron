package com.aaron.fsh.user.service;
import org.gordianknot.common.util.JWTUtils;
import com.aaron.fsh.user.po.EnterpriseProductUser;
import org.gordianknot.jdbc.EntityService;
import org.springframework.stereotype.Service;
/**
 * 企業用戶業務
 * @author aaron
 **/
@Service
public class EnterpriseProductUserServiceImpl extends EntityService<EnterpriseProductUser> implements EnterpriseProductUserService {
    @Override
    public String login(Long eid, String uid) {
        JWTUtils jwtUtils = JWTUtils.getInstance(System.getProperty("rsa.modulus"), System.getProperty("rsa.privateExponent"), System.getProperty("rsa.publicExponent"));
        if (eid.equals(1L) && uid.equals("1001")) {
            return jwtUtils.getToken(uid);
        }
        return null;
    }
}
