package com.pro.admin.server.component.captcha;

import com.pro.starter.captcha.core.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lijunping on 2021/12/22
 */
@Slf4j
@Component
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("向手机号{}发送了验证码{}", mobile, code);
    }
}
