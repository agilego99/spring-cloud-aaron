package com.aaron.api.client.auth;

import com.aaron.api.client.auth.query.AuthQuery;
import com.aaron.api.client.config.FeignConfiguration;
import org.gordianknot.common.base.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 房生活認證服務 API 調用客戶端
 * @author aaron
 **/
@FeignClient(value = "aaron-auth-service", path = "/oauth", configuration = FeignConfiguration.class, fallback = AuthRemoteClientHystrix.class)
public interface AuthRemoteClient {

    /**
     * 調用認證，獲取 Token
     * @param query
     * @return
     */
    @PostMapping("/token")
    ResponseData auth(@RequestBody AuthQuery query);

}
