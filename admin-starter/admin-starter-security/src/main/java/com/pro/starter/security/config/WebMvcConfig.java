package com.pro.starter.security.config;


import com.pro.starter.security.interceptor.SecurityInterceptor;
import com.pro.starter.security.properties.SecurityProperties;
import com.pro.starter.security.service.AuthorityService;
import com.pro.starter.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 拦截器相关配置，在 WebAutoConfiguration 之后自动配置，保证过滤器的顺序
 */
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(SecurityProperties.class)
public class WebMvcConfig implements WebMvcConfigurer {
  private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

  private final SecurityProperties securityProperties;

  private final TokenService tokenService;

  private final AuthorityService authorityService;

  public WebMvcConfig(SecurityProperties securityProperties, TokenService tokenService, AuthorityService authorityService) {
    this.securityProperties = securityProperties;
    this.tokenService = tokenService;
    this.authorityService = authorityService;
  }

  @Bean
  public SecurityInterceptor securityInterceptor(){
    return new SecurityInterceptor(tokenService, authorityService);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(securityInterceptor())
      .excludePathPatterns(securityProperties.getIgnorePaths())
      .excludePathPatterns(securityProperties.getDefaultIgnorePaths());
  }

}
