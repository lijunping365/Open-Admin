package com.pro.starter.captcha.core.sms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.pro.starter.captcha.core.domain.ValidateCode;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *  @author : lijunping
 *  @weixin : ilwq18242076871
 * 该类默认就是短信验证码
 * Description: 存放到redis里的对象必须是序列化的，所以这里要实现Serializable接口
 */
@Data
public class SmsValidateCode extends ValidateCode implements Serializable {

  private static final long serialVersionUID = -5882129757498488074L;

  /**
   * 随机验证码
   */
  private String code;

  /**
   * 本地当前时间
   */
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime expireTime;

  public SmsValidateCode(String code, int expireIn) {
    this.code = code;
    expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  public SmsValidateCode(String code, LocalDateTime expireTime) {
    this.code = code;
    this.expireTime = expireTime;
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
