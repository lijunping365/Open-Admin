package com.pro.starter.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 安全相关配置
 */
@Data
@ConfigurationProperties(prefix = "com.pro.security")
public class SecurityProperties {

  private static final String[] DEFAULT_IGNORE_PATHS = new String[]{
      // Swagger 相关
      "/doc.html", "/swagger-resources", "/swagger-resources/**", "/webjars/**", "v2/**", "/favicon.ico",
      // Actuator 相关

      // 登录相关
      "/login/**", "/validate/code/**"
  };

  /**
   * 自定义忽略 Path， 白名单配置
   */
  private String[] ignorePaths = new String[0];
  /**
   * 默认忽略 Path
   */
  private String[] defaultIgnorePaths = DEFAULT_IGNORE_PATHS;

  public String[] getIgnorePaths() {
    return ignorePaths;
  }

  public SecurityProperties setIgnorePaths(String[] ignorePaths) {
    this.ignorePaths = ignorePaths;
    return this;
  }

  public String[] getDefaultIgnorePaths() {
    return defaultIgnorePaths;
  }

  public SecurityProperties setDefaultIgnorePaths(String[] defaultIgnorePaths) {
    this.defaultIgnorePaths = defaultIgnorePaths;
    return this;
  }

}
