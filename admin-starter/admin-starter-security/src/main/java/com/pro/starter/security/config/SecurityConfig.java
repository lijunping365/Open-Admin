package com.pro.starter.security.config;

import com.pro.starter.security.interceptor.SecurityInterceptor;
import com.pro.starter.security.properties.SecurityProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lijunping on 2022/1/11
 */
public class SecurityConfig implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    private final SecurityInterceptor securityInterceptor;

    public SecurityConfig(SecurityProperties securityProperties, SecurityInterceptor securityInterceptor) {
        this.securityProperties = securityProperties;
        this.securityInterceptor = securityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .excludePathPatterns(securityProperties.getIgnorePaths())
                .excludePathPatterns(securityProperties.getDefaultIgnorePaths());
    }
}
