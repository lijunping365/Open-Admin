package com.pro.admin.server.controller;

import com.pro.oauth.common.vo.Result;
import com.pro.oauth.server.dto.req.BaseOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.MobileOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.PasswordOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.ValidateCodeVerifyReqDTO;
import com.pro.oauth.server.dto.resp.OAuthLoginRespDTO;
import com.pro.oauth.server.rpc.OAuthLoginRpc;
import com.pro.oauth.server.rpc.ValidateCodeRpc;
import io.swagger.annotations.ApiOperation;
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
@Validated
@RestController
@RequestMapping("/login")
public class LoginController {

    @DubboReference
    private OAuthLoginRpc oAuthLoginRpc;

    @DubboReference
    private ValidateCodeRpc validateCodeRpc;

    @ApiOperation("用户名密码登录")
    @PostMapping("/account")
    public Result<OAuthLoginRespDTO> loginByUsername(@RequestBody @Valid PasswordOAuthLoginReqDTO request){
        Result<Boolean> validateResult = validateCaptcha(request);
        if (!validateResult.isSuccess() || !validateResult.getData()){
            return Result.failed("验证码输入错误");
        }
        return oAuthLoginRpc.loginByUsername(request);
    }

    @ApiOperation("手机号短信验证码登录")
    @PostMapping("/mobile")
    public Result<OAuthLoginRespDTO> loginByMobile(@RequestBody @Valid MobileOAuthLoginReqDTO request){
        Result<Boolean> validateResult = validateCaptcha(request);
        if (!validateResult.isSuccess() || !validateResult.getData()){
            return Result.failed("验证码输入错误");
        }
        return oAuthLoginRpc.loginByMobile(request);
    }

    private Result<Boolean> validateCaptcha(BaseOAuthLoginReqDTO request){
        ValidateCodeVerifyReqDTO validateCodeVerifyReqDTO = new ValidateCodeVerifyReqDTO();
        validateCodeVerifyReqDTO.setDeviceId(request.getDeviceId());
        validateCodeVerifyReqDTO.setCode(request.getCaptcha());
        return validateCodeRpc.validate(validateCodeVerifyReqDTO);
    }
}
