package com.pro.admin.server.service;

import com.pro.starter.captcha.core.image.ImageCaptchaRequest;
import com.pro.starter.captcha.core.qr.QrCodeCaptchaRequest;
import com.pro.starter.captcha.core.sms.SmsCaptchaRequest;
import com.pro.starter.captcha.request.CaptchaVerifyRequest;

/**
 * @author lijunping on 2021/12/21
 */
public interface ValidateCodeService {

    Boolean sendImageCaptcha(ImageCaptchaRequest request);

    Boolean sendSmsCaptcha(SmsCaptchaRequest request);

    Boolean sendQrCodeCaptcha(QrCodeCaptchaRequest request);

    Boolean validate(CaptchaVerifyRequest request);


}
