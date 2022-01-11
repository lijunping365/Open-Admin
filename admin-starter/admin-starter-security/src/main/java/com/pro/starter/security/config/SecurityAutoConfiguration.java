package com.pro.starter.security.config;


import com.pro.starter.security.interceptor.SecurityInterceptor;
import com.pro.starter.security.properties.SecurityProperties;
import com.pro.starter.security.service.AuthorityService;
import com.pro.starter.security.service.TokenService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 */
@Configuration
//在 WebAutoConfiguration 之后自动配置，保证过滤器的顺序
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
//这里的意思就是判断当前应用是否是web应用，如果是，当前配置类生效
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//判断系统中有没有 AuthorityService 和 TokenService 这两个类，如果有配置类才生效
@ConditionalOnClass({AuthorityService.class, TokenService.class})
//判断配置文件中是否存在某个配置：com.pro.security.enabled；matchIfMissing = true表明即使我们配置文件中不配置 com.pro.security.enabled=true，该配置类也是默认生效的；
@ConditionalOnProperty(value = "com.pro.security.enabled", matchIfMissing = true)
//将与配置文件绑定好的某个类注入到容器中，使其生效
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfiguration {

  private final SecurityProperties securityProperties;

  private final TokenService tokenService;

  private final AuthorityService authorityService;

  public SecurityAutoConfiguration(SecurityProperties securityProperties, TokenService tokenService, AuthorityService authorityService) {
    this.securityProperties = securityProperties;
    this.tokenService = tokenService;
    this.authorityService = authorityService;
  }

  @Bean
  public SecurityInterceptor securityInterceptor(){
    return new SecurityInterceptor(tokenService, authorityService);
  }

  @Bean
  public SecurityConfig securityConfig(){
    return new SecurityConfig(securityProperties, securityInterceptor());
  }

}
