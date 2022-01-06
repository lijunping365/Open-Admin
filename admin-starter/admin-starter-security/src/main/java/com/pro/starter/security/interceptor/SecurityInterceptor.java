package com.pro.starter.security.interceptor;

import com.pro.admin.common.constants.CommonConstant;
import com.pro.starter.security.exception.SecurityException;
import com.pro.admin.common.exception.ServiceException;
import com.pro.admin.common.json.JSON;
import com.pro.admin.common.vo.ResultEnum;
import com.pro.starter.security.annotation.PreAuthorization;
import com.pro.starter.security.service.AuthorityService;
import com.pro.starter.security.service.TokenService;
import com.pro.starter.security.context.UserSecurityContext;
import com.pro.starter.security.context.UserSecurityContextHolder;
import com.pro.starter.security.domain.Authentication;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 认证通过且角色匹配的用户可访问当前路径
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 认证拦截器
 */
@Slf4j
@Data
public class SecurityInterceptor implements HandlerInterceptor {

  private final TokenService tokenService;

  private final AuthorityService authorityService;

  public SecurityInterceptor(TokenService tokenService, AuthorityService authorityService){
    this.tokenService = tokenService;
    this.authorityService = authorityService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    try {
      String accessToken = extractTokenFromHeader(request);
      Authentication authentication = tokenService.readAuthentication(accessToken);
      UserSecurityContext user = JSON.parse(authentication.getUserDetails(), UserSecurityContext.class);
      UserSecurityContextHolder.setContext(user);
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      PreAuthorization authorization = handlerMethod.getMethodAnnotation(PreAuthorization.class);
      if (Objects.isNull(authorization)) {
        return true;
      }

      List<String> authorities = getAuthorities(request);
      if (!CollectionUtils.containsAny(authorities, user.getAuthorities())) {
        throw new SecurityException(ResultEnum.FORBIDDEN);
      }
      return true;
    }catch (Exception e){
      throw new ServiceException(ResultEnum.UNAUTHORIZED);
    }
  }

  private String extractTokenFromHeader(HttpServletRequest request) {
    String header = request.getHeader(CommonConstant.AUTHENTICATION_HEADER);
    if (header == null || !header.startsWith(CommonConstant.AUTHENTICATION_TYPE)) {
      throw new SecurityException("错误的请求头");
    }
    return header.substring(7);
  }

  private List<String> getAuthorities(HttpServletRequest request){
    PathMatcher pathMatcher = new AntPathMatcher();
    Map<String, List<String>> authoritiesMap = authorityService.getAuthorities();
    if (CollectionUtils.isEmpty(authoritiesMap)) {
      return Collections.emptyList();
    }
    Iterator<String> iterator = authoritiesMap.keySet().iterator();
    List<String> authorities = new ArrayList<>();
    while (iterator.hasNext()) {
      String pattern = iterator.next();
      if (pathMatcher.match(pattern, request.getRequestURI())) {
        authorities.addAll(authoritiesMap.get(pattern));
      }
    }
    return authorities;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    UserSecurityContextHolder.clear();
  }
}
