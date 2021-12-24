package com.pro.admin.server.component.authority;

import com.pro.starter.security.service.AuthorityService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lijunping on 2021/12/24
 */
@Component
public class DefaultAuthorityService implements AuthorityService {


    @Override
    public Map<String, List<String>> getAuthorities() {
        return null;
    }
}
