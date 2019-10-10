package com.aaron.api.client.auth;

import com.aaron.api.client.auth.query.AuthQuery;
import org.gordianknot.common.base.ResponseData;
import org.springframework.stereotype.Component;

/**
 * @author aaron
 **/
@Component
public class AuthRemoteClientHystrix implements AuthRemoteClient {

    @Override
    public ResponseData auth(AuthQuery query) {
        return ResponseData.ok("");
    }
}
