package com.pro.admin.server.service.impl;

import com.pro.admin.server.service.ValidateCodeService;
import com.pro.starter.captcha.core.image.ImageCaptchaRequest;
import com.pro.starter.captcha.core.qr.QrCodeCaptchaRequest;
import com.pro.starter.captcha.core.sms.SmsCaptchaRequest;
import com.pro.starter.captcha.exception.ValidateCodeException;
import com.pro.starter.captcha.processor.ValidateCodeProcessor;
import com.pro.starter.captcha.request.CaptchaVerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author lijunping on 2021/12/21
 */
@Slf4j
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    @Qualifier("imageCodeProcessor")
    private ValidateCodeProcessor<ImageCaptchaRequest> imageValidateCodeProcessor;

    @Autowired
    @Qualifier("smsCodeProcessor")
    private ValidateCodeProcessor<SmsCaptchaRequest> smsValidateCodeProcessor;

    @Autowired
    @Qualifier("qrCodeCodeProcessor")
    private ValidateCodeProcessor<QrCodeCaptchaRequest> qrCodeValidateCodeProcessor;

    @Override
    public Boolean sendImageCaptcha(ImageCaptchaRequest request) {
        try {
            imageValidateCodeProcessor.process(request);
            return true;
        }catch (ValidateCodeException e){
            return false;
        }
    }

    @Override
    public Boolean sendSmsCaptcha(SmsCaptchaRequest request) {
        try {
            smsValidateCodeProcessor.process(request);
            return true;
        } catch (ValidateCodeException e){
            return false;
        }
    }

    @Override
    public Boolean sendQrCodeCaptcha(QrCodeCaptchaRequest request) {
        try {
            qrCodeValidateCodeProcessor.process(request);
            return true;
        } catch (ValidateCodeException e){
            return false;
        }
    }

    @Override
    public Boolean validate(CaptchaVerifyRequest request) {
        try {
            smsValidateCodeProcessor.validate(request);
            return true;
        } catch (ValidateCodeException exception) {
            log.info("[验证码验证失败]:{}", exception.getMessage());
            return false;
        }
    }
}
