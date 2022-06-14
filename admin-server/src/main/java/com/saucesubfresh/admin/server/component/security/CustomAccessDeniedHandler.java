package com.saucesubfresh.admin.server.component.security;

import com.saucesubfresh.admin.server.service.SysRoleMenuService;
import com.saucesubfresh.starter.security.authorization.AccessDeniedHandler;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import com.saucesubfresh.starter.security.enums.SecurityExceptionEnum;
import com.saucesubfresh.starter.security.exception.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @author: 李俊平
 * @Date: 2022-05-04 20:35
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Value("#{'${com.saucesubfresh.security.white-paths:}'.split(',')}")
    private Set<String> whitePaths;

    @Override
    public boolean handler(HttpServletRequest request, Object o) throws SecurityException {
        if (matcher(request, whitePaths)){
            return Boolean.TRUE;
        }

        Set<String> authorities;
        try {
            List<String> roles = UserSecurityContextHolder.getContext().getAuthorities();
            authorities = roleMenuService.getAuthorities(roles);
        }catch (Exception e){
            throw new SecurityException(SecurityExceptionEnum.FORBIDDEN.getCode(), e.getMessage());
        }

        if (CollectionUtils.isEmpty(authorities) || !matcher(request, authorities)) {
            throw new SecurityException(SecurityExceptionEnum.FORBIDDEN);
        }
        return Boolean.TRUE;
    }

    private boolean matcher(HttpServletRequest request, Set<String> paths){
        PathPattern pattern = PathPatternParser.defaultInstance.parse(request.getRequestURI());
        boolean match = false;
        for (String path : paths) {
            if (pattern.matches(PathContainer.parsePath(path))) {
                match = true;
            }
        }
        return match;
    }
}
