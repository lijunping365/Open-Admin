package com.pro.starter.captcha.repository;


import com.pro.starter.captcha.core.domain.ValidateCode;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description：保存、获取、移除验证码的模版接口
 */
public interface ValidateCodeRepository {
    /**
     * 保存验证码
     * @param deviceId
     * @param code
     */
    void save(String deviceId, ValidateCode code);
    /**
     * 获取验证码
     * @param deviceId
     * @return
     */
    ValidateCode get(String deviceId);
    /**
     * 移除验证码
     * @param deviceId
     */
    void remove(String deviceId);
}
