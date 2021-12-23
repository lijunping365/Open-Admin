package com.pro.starter.captcha.processor;

import com.pro.starter.captcha.exception.ValidateCodeException;
import com.pro.starter.captcha.request.BaseCaptchaRequest;
import com.pro.starter.captcha.request.CaptchaVerifyRequest;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description:校验码处理器，封装不同校验码的处理逻辑
 */
public interface ValidateCodeProcessor<T extends BaseCaptchaRequest> {
    /**
     * 创建校验码，保存验证码，发送验证码
     * @param request
     */
    void process(T request) throws ValidateCodeException;
    /**
     * 校验验证码
     *
     * @param captchaVerifyRequest
     * @throws Exception
     */
    void validate(CaptchaVerifyRequest captchaVerifyRequest) throws ValidateCodeException;
}
