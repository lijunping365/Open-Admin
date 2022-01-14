package com.pro.admin.server.controller;

import com.pro.admin.server.dto.req.SysMobileOAuthLoginReqDTO;
import com.pro.admin.server.dto.req.SysPasswordOAuthLoginReqDTO;
import com.pro.admin.server.dto.resp.SysOAuthLoginRespDTO;
import com.pro.admin.server.service.SysLoginService;
import com.pro.oauth.common.vo.Result;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private SysLoginService loginService;

    @ApiOperation("用户名密码登录")
    @PostMapping("/account")
    public Result<SysOAuthLoginRespDTO> loginByUsername(@RequestBody SysPasswordOAuthLoginReqDTO request){
        return Result.succeed(loginService.loginByUsername(request));
    }

    @ApiOperation("手机号短信验证码登录")
    @PostMapping("/mobile")
    public Result<SysOAuthLoginRespDTO> loginByMobile(@RequestBody @Valid SysMobileOAuthLoginReqDTO request){
        return Result.succeed(loginService.loginByMobile(request));
    }


}
