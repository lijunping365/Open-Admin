package com.pro.admin.server.component.token;

import com.pro.starter.security.domain.Authentication;
import com.pro.starter.security.service.TokenService;
import org.springframework.stereotype.Component;

/**
 * @author lijunping on 2021/12/24
 */
@Component
public class DefaultTokenService implements TokenService {

    @Override
    public Authentication readAuthentication(String accessToken) {
        return null;
    }
}
