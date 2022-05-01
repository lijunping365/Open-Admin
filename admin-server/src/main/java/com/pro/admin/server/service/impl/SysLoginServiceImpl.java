package com.pro.admin.server.service.impl;

import com.pro.admin.common.exception.ServiceException;
import com.pro.admin.server.convert.SysOAuthLoginConvert;
import com.pro.admin.server.dto.req.SysMobileOAuthLoginReqDTO;
import com.pro.admin.server.dto.req.SysPasswordOAuthLoginReqDTO;
import com.pro.admin.server.dto.resp.SysOAuthLoginRespDTO;
import com.pro.admin.server.service.SysLoginService;
import com.pro.admin.server.service.ValidateCodeService;
import com.pro.oauth.common.enums.UserTypeEnum;
import com.pro.oauth.common.vo.Result;
import com.pro.oauth.server.dto.req.MobileOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.PasswordOAuthLoginReqDTO;
import com.pro.oauth.server.dto.resp.OAuthLoginRespDTO;
import com.pro.oauth.server.rpc.OAuthLoginRpc;
import com.pro.starter.captcha.request.CaptchaVerifyRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijunping on 2022/1/14
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @DubboReference(validation = "true")
    private OAuthLoginRpc oAuthLoginRpc;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public SysOAuthLoginRespDTO loginByUsername(SysPasswordOAuthLoginReqDTO request) {
        Boolean validateResult = validateCaptcha(request.getCaptcha());
        if (!validateResult){
            throw new ServiceException("验证码输入错误");
        }
        PasswordOAuthLoginReqDTO reqDTO = SysOAuthLoginConvert.INSTANCE.convertPasswordDTO(request);
        reqDTO.setUserType(UserTypeEnum.MANAGEMENT.name());
        final Result<OAuthLoginRespDTO> oAuthLoginRespDTOResult = oAuthLoginRpc.loginByUsername(reqDTO);
        return null;
    }

    @Override
    public SysOAuthLoginRespDTO loginByMobile(SysMobileOAuthLoginReqDTO request) {
        Boolean validateResult = validateCaptcha(request.getCaptcha());
        if (!validateResult){
            throw new ServiceException("验证码输入错误");
        }
        MobileOAuthLoginReqDTO reqDTO = SysOAuthLoginConvert.INSTANCE.convertMobileDTO(request);
        reqDTO.setUserType(UserTypeEnum.MANAGEMENT.name());
        final Result<OAuthLoginRespDTO> oAuthLoginRespDTOResult = oAuthLoginRpc.loginByMobile(reqDTO);
        return null;
    }

    private Boolean validateCaptcha(String captcha){
        CaptchaVerifyRequest validateCodeVerifyReqDTO = new CaptchaVerifyRequest();
        validateCodeVerifyReqDTO.setCode(captcha);
        return validateCodeService.validate(validateCodeVerifyReqDTO);
    }
}
