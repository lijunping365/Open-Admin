package com.pro.starter.captcha.enums;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * @Description： 利用interface封装常量的好处是不用写public static final三个关键字
 * 因为interface中常量会默认加上这三个关键字
 */
public interface CaptchaConstants {
  /**
   * 验证码保存到 redis 时的 Key
   */
  String VALIDATE_CODE_KEY_FOR_REDIS_PREFIX = "CAPTCHA:CODE:";

}
