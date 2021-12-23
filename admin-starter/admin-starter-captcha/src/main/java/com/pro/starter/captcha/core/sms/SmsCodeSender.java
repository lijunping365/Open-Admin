package com.pro.starter.captcha.core.sms;

/**
 * @author lijunping on 2021/12/22
 */
public interface SmsCodeSender {

    /**
     * 向手机发送短信验证码
     *
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
