package com.pro.starter.security.config;


import com.pro.starter.security.interceptor.SecurityInterceptor;
import com.pro.starter.security.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
  private final SecurityInterceptor securityInterceptor;

  public WebMvcConfig(SecurityProperties securityProperties, SecurityInterceptor securityInterceptor) {
    this.securityProperties = securityProperties;
    this.securityInterceptor = securityInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(securityInterceptor)
      .excludePathPatterns(securityProperties.getIgnorePaths())
      .excludePathPatterns(securityProperties.getDefaultIgnorePaths());
    logger.info("[addInterceptors][加载 SecurityInterceptor 拦截器完成]");
  }

}
