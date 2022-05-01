package com.pro.admin.server.controller;

import com.pro.admin.server.service.ValidateCodeService;
import com.pro.oauth.common.vo.Result;
import com.pro.starter.captcha.core.image.ImageCaptchaRequest;
import com.pro.starter.captcha.core.qr.QrCodeCaptchaRequest;
import com.pro.starter.captcha.core.sms.SmsCaptchaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lijunping on 2021/12/22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private ValidateCodeService validateCodeService;

    @PostMapping("/sendImageCaptcha")
    public Result<Boolean> sendImageCaptcha(@RequestBody @Valid ImageCaptchaRequest request){
        return Result.succeed(validateCodeService.sendImageCaptcha(request));
    }

    @PostMapping("/sendSmsCaptcha")
    public Result<Boolean> sendSmsCaptcha(@RequestBody @Valid SmsCaptchaRequest request){
        return Result.succeed(validateCodeService.sendSmsCaptcha(request));
    }

    @PostMapping("/sendQrCodeCaptcha")
    public Result<Boolean> sendSmsCaptcha(@RequestBody @Valid QrCodeCaptchaRequest request){
        return Result.succeed(validateCodeService.sendQrCodeCaptcha(request));
    }
}
