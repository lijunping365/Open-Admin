package com.pro.admin.server.controller;

import com.pro.oauth.common.vo.Result;
import com.pro.oauth.server.dto.req.ImageCaptchaReqDTO;
import com.pro.oauth.server.dto.req.QrCodeCaptchaReqDTO;
import com.pro.oauth.server.dto.req.SmsCaptchaReqDTO;
import com.pro.oauth.server.rpc.ValidateCodeRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference
    private ValidateCodeRpc validateCodeRpc;

    @PostMapping("/sendImageCaptcha")
    public Result<String> sendImageCaptcha(@RequestBody @Valid ImageCaptchaReqDTO request){
        Result<Boolean> result = validateCodeRpc.sendImageCaptcha(request);
        if (result.isSuccess() && result.getData()){
            return Result.succeed("验证码已发送，请注意查收");
        }
        return Result.failed("验证码发送失败");
    }

    @PostMapping("/sendSmsCaptcha")
    public Result<String> sendSmsCaptcha(@RequestBody @Valid SmsCaptchaReqDTO request){
        Result<Boolean> result = validateCodeRpc.sendSmsCaptcha(request);
        if (result.isSuccess() && result.getData()){
            return Result.succeed("验证码已发送，请注意查收");
        }
        return Result.failed("验证码发送失败");
    }

    @PostMapping("/sendQrCodeCaptcha")
    public Result<String> sendSmsCaptcha(@RequestBody @Valid QrCodeCaptchaReqDTO request){
        Result<Boolean> result = validateCodeRpc.sendQrCodeCaptcha(request);
        if (result.isSuccess() && result.getData()){
            return Result.succeed("验证码已发送，请注意查收");
        }
        return Result.failed("验证码发送失败");
    }
}
