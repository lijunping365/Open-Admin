package com.pro.admin.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

  @Autowired
  private RedisSerializer jackson2JsonRedisSerializer;

  @Override
  @Bean
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator() {

      @Override
      public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(".").append(method.getName());

        StringBuilder paramsSb = new StringBuilder();
        for (Object param : params) {
          // 如果不指定，默认生成包含到键值中
          if (param != null) {
            paramsSb.append(param.toString());
          }
        }

        if (paramsSb.length() > 0) {
          sb.append("_").append(paramsSb);
        }
        return sb.toString();
      }

    };

  }

  @Bean
  public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
    CacheProperties.Redis redisProperties = cacheProperties.getRedis();
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
    config = config
        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
    // 将配置文件中的配置都生效
    if (redisProperties.getTimeToLive() != null) {
      config = config.entryTtl(redisProperties.getTimeToLive());
    }

    if (redisProperties.getKeyPrefix() != null) {
      config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
    }

    if (!redisProperties.isCacheNullValues()) {
      config = config.disableCachingNullValues();
    }

    if (!redisProperties.isUseKeyPrefix()) {
      config = config.disableKeyPrefix();
    }

    return config;
  }


}
