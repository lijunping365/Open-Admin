package com.saucesubfresh.admin.server.component.security;

import com.saucesubfresh.admin.common.vo.ResultEnum;
import com.saucesubfresh.starter.security.authorization.AccessDeniedHandler;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import com.saucesubfresh.starter.security.exception.AccessDeniedException;
import com.saucesubfresh.starter.security.exception.SecurityException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: 李俊平
 * @Date: 2022-05-04 20:35
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public boolean handler(HttpServletRequest request, Object o) throws SecurityException {
        List<String> authorities = UserSecurityContextHolder.getContext().getAuthorities();
        if (CollectionUtils.isEmpty(authorities) || !matcher(request, authorities)) {
            throw new AccessDeniedException(ResultEnum.FORBIDDEN.getMsg());
        }
        return Boolean.TRUE;
    }

    private boolean matcher(HttpServletRequest request, List<String> paths){
        PathMatcher pathMatcher = new AntPathMatcher();
        String servletPath = request.getServletPath();
        boolean match = false;
        for (String path : paths) {
            if (pathMatcher.match(path, servletPath)) {
                match = true;
            }
        }
        return match;
    }
}
