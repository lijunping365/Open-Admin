package com.pro.admin.server.controller;

import com.pro.admin.server.service.ValidateCodeService;
import com.pro.oauth.common.vo.Result;
import com.pro.oauth.server.dto.req.BaseOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.MobileOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.PasswordOAuthLoginReqDTO;
import com.pro.oauth.server.dto.resp.OAuthLoginRespDTO;
import com.pro.oauth.server.rpc.OAuthLoginRpc;
import com.pro.starter.captcha.request.CaptchaVerifyRequest;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
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
@Validated
@RestController
@RequestMapping("/login")
public class LoginController {

    @DubboReference
    private OAuthLoginRpc oAuthLoginRpc;

    @Autowired
    private ValidateCodeService validateCodeService;

    @ApiOperation("用户名密码登录")
    @PostMapping("/account")
    public Result<OAuthLoginRespDTO> loginByUsername(@RequestBody @Valid PasswordOAuthLoginReqDTO request){
        Boolean validateResult = validateCaptcha(request);
        if (!validateResult){
            return Result.failed("验证码输入错误");
        }
        return oAuthLoginRpc.loginByUsername(request);
    }

    @ApiOperation("手机号短信验证码登录")
    @PostMapping("/mobile")
    public Result<OAuthLoginRespDTO> loginByMobile(@RequestBody @Valid MobileOAuthLoginReqDTO request){
        Boolean validateResult = validateCaptcha(request);
        if (!validateResult){
            return Result.failed("验证码输入错误");
        }
        return oAuthLoginRpc.loginByMobile(request);
    }

    private Boolean validateCaptcha(BaseOAuthLoginReqDTO request){
        CaptchaVerifyRequest validateCodeVerifyReqDTO = new CaptchaVerifyRequest();
        validateCodeVerifyReqDTO.setDeviceId(request.getDeviceId());
        validateCodeVerifyReqDTO.setCode(request.getCaptcha());
        return validateCodeService.validate(validateCodeVerifyReqDTO);
    }
}
