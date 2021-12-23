package com.pro.starter.captcha.core.sms;


import com.pro.starter.captcha.processor.AbstractValidateCodeProcessor;
import com.pro.starter.captcha.properties.CaptchaProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * @Description: 注意验证码+ 超时时间可以进行配置
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCaptchaRequest, SmsValidateCode> {

    @Autowired
    private CaptchaProperties captchaProperties;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    public SmsValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(captchaProperties.getCode().getSms().getLength());
        return new SmsValidateCode(code, captchaProperties.getCode().getSms().getExpireTime());
    }

    @Override
    protected void send(SmsCaptchaRequest request, SmsValidateCode code) {
        smsCodeSender.send(request.getMobile(), code.getCode());
    }

}
