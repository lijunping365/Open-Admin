package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.common.vo.ResultEnum;
import com.saucesubfresh.admin.server.dto.req.SysMobileLoginRequest;
import com.saucesubfresh.admin.server.dto.req.SysPasswordLoginRequest;
import com.saucesubfresh.starter.captcha.exception.ValidateCodeException;
import com.saucesubfresh.starter.captcha.processor.CaptchaVerifyProcessor;
import com.saucesubfresh.starter.captcha.request.CaptchaVerifyRequest;
import com.saucesubfresh.starter.oauth.core.password.PasswordAuthenticationProcessor;
import com.saucesubfresh.starter.oauth.core.sms.SmsMobileAuthenticationProcessor;
import com.saucesubfresh.starter.oauth.exception.AuthenticationException;
import com.saucesubfresh.starter.oauth.exception.UsernameNotFoundException;
import com.saucesubfresh.starter.oauth.request.MobileLoginRequest;
import com.saucesubfresh.starter.oauth.request.PasswordLoginRequest;
import com.saucesubfresh.starter.oauth.token.AccessToken;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/login")
public class SysLoginController {

    private final CaptchaVerifyProcessor captchaVerifyProcessor;
    private final PasswordAuthenticationProcessor passwordAuthentication;
    private final SmsMobileAuthenticationProcessor smsMobileAuthentication;

    public SysLoginController(CaptchaVerifyProcessor captchaVerifyProcessor,
                              PasswordAuthenticationProcessor passwordAuthentication,
                              SmsMobileAuthenticationProcessor smsMobileAuthentication) {
        this.captchaVerifyProcessor = captchaVerifyProcessor;
        this.passwordAuthentication = passwordAuthentication;
        this.smsMobileAuthentication = smsMobileAuthentication;
    }

    /**
     * 用户名密码登录
     * @param request
     * @return
     */
    @PostMapping("/account")
    public Result<AccessToken> loginByUsername(@RequestBody @Valid SysPasswordLoginRequest request){
        CaptchaVerifyRequest captchaVerifyRequest = new CaptchaVerifyRequest()
                .setRequestId(request.getDeviceId())
                .setCode(request.getCaptcha());
        try {
            captchaVerifyProcessor.validate(captchaVerifyRequest);
        } catch (ValidateCodeException e){
            return Result.failed(e.getMessage());
        }

        PasswordLoginRequest passwordLoginRequest = new PasswordLoginRequest()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword());
        try {
            final AccessToken accessToken = passwordAuthentication.authentication(passwordLoginRequest);
            return Result.succeed(accessToken);
        } catch (AuthenticationException e){
            if(e instanceof UsernameNotFoundException){
                return Result.failed(ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMsg());
            }
            return Result.failed(e.getMessage());
        }
    }

    /**
     * 手机号短信验证码登录
     * @param request
     * @return
     */
    @PostMapping("/mobile")
    public Result<AccessToken> loginByMobile(@RequestBody @Valid SysMobileLoginRequest request){
        CaptchaVerifyRequest captchaVerifyRequest = new CaptchaVerifyRequest()
                .setRequestId(request.getDeviceId())
                .setCode(request.getCaptcha());
        try {
            captchaVerifyProcessor.validate(captchaVerifyRequest);
        } catch (ValidateCodeException e){
            return Result.failed(e.getMessage());
        }

        MobileLoginRequest mobileLoginRequest = new MobileLoginRequest().setMobile(request.getMobile());
        try {
            final AccessToken accessToken = smsMobileAuthentication.authentication(mobileLoginRequest);
            return Result.succeed(accessToken);
        } catch (AuthenticationException e){
            return Result.failed(e.getMessage());
        }
    }
}
