package com.pro.starter.captcha.config;


import com.pro.starter.captcha.properties.CaptchaProperties;
import com.pro.starter.captcha.repository.ValidateCodeRepository;
import com.pro.starter.captcha.repository.redis.RedisValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CaptchaProperties.class)
public class ValidateCodeBeanConfig {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Bean
  @ConditionalOnMissingBean
  public ValidateCodeRepository validateCodeRepository(){
    return new RedisValidateCodeRepository(redisTemplate);
  }
}
