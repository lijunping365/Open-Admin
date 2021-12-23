package com.pro.starter.captcha.repository.redis;


import com.pro.starter.captcha.core.domain.ValidateCode;
import com.pro.starter.captcha.enums.CaptchaConstants;
import com.pro.starter.captcha.exception.ValidateCodeException;
import com.pro.starter.captcha.repository.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 *
 * Description：使用redis+deviceId的方式进行验证码的存、取、删
 */
public class RedisValidateCodeRepository implements ValidateCodeRepository {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisValidateCodeRepository(RedisTemplate<String, Object> redisTemplate){
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void save(String deviceId, ValidateCode code) {
    redisTemplate.opsForValue().set(buildKey(deviceId), code, code.getExpireTime().getMinute(), TimeUnit.MINUTES);
  }

  @Override
  public ValidateCode get(String deviceId) {
    Object value = redisTemplate.opsForValue().get(buildKey(deviceId));
    if (value == null) {
      return null;
    }
    return (ValidateCode) value;
  }

  @Override
  public void remove(String deviceId) {
    redisTemplate.delete(buildKey(deviceId));
  }

  /**
   * 构建验证码在redis中的key ---- 该key类似与cookie
   *
   * @param deviceId
   * @return
   */
  private String buildKey(String deviceId) {
    if (StringUtils.isBlank(deviceId)) {
      throw new ValidateCodeException("请在请求头中携带deviceId参数");
    }
    return CaptchaConstants.VALIDATE_CODE_KEY_FOR_REDIS_PREFIX + ":" + deviceId;
  }
}
