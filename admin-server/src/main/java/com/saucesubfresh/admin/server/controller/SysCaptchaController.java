package com.saucesubfresh.admin.server.controller;

import com.saucesubfresh.admin.common.exception.ControllerException;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.admin.server.dto.create.SysCaptchaRespDTO;
import com.saucesubfresh.admin.server.dto.req.SysCaptchaReqDTO;
import com.saucesubfresh.starter.captcha.core.image.ImageCodeGenerator;
import com.saucesubfresh.starter.captcha.core.image.ImageValidateCode;
import com.saucesubfresh.starter.captcha.core.math.MathImageCodeGenerator;
import com.saucesubfresh.starter.captcha.core.sms.SmsCodeGenerator;
import com.saucesubfresh.starter.captcha.core.sms.ValidateCode;
import com.saucesubfresh.starter.captcha.exception.ValidateCodeException;
import com.saucesubfresh.starter.captcha.request.CaptchaGenerateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author lijunping on 2021/12/22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/captcha")
public class SysCaptchaController {

    private final ImageCodeGenerator imageCodeGenerator;
    private final SmsCodeGenerator smsCodeGenerator;
    private final MathImageCodeGenerator mathImageCodeGenerator;

    public SysCaptchaController(ImageCodeGenerator imageCodeGenerator,
                                    SmsCodeGenerator smsCodeGenerator,
                                    MathImageCodeGenerator mathImageCodeGenerator) {
        this.imageCodeGenerator = imageCodeGenerator;
        this.smsCodeGenerator = smsCodeGenerator;
        this.mathImageCodeGenerator = mathImageCodeGenerator;
    }

    @PostMapping("/create/mathImage")
    public Result<SysCaptchaRespDTO> createMathImageCode(@RequestBody @Valid SysCaptchaReqDTO request) {
        CaptchaGenerateRequest captchaGenerateRequest = new CaptchaGenerateRequest();
        captchaGenerateRequest.setRequestId(request.getDeviceId());
        try {
            ImageValidateCode imageValidateCode = mathImageCodeGenerator.create(captchaGenerateRequest);
            return Result.succeed(convert(imageValidateCode));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ControllerException(e.getMessage());
        }
    }

    @PostMapping("/create/image")
    public Result<SysCaptchaRespDTO> createImageCode(@RequestBody @Valid SysCaptchaReqDTO request) {
        CaptchaGenerateRequest captchaGenerateRequest = new CaptchaGenerateRequest();
        captchaGenerateRequest.setRequestId(request.getDeviceId());
        try {
            ImageValidateCode imageValidateCode = imageCodeGenerator.create(captchaGenerateRequest);
            return Result.succeed(convert(imageValidateCode));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ControllerException(e.getMessage());
        }
    }

    @PostMapping("/create/sms")
    public Result<SysCaptchaRespDTO> createSmsCode(@RequestBody @Valid SysCaptchaReqDTO request) {
        SysCaptchaRespDTO crawlerCaptchaRespDTO = new SysCaptchaRespDTO();
        CaptchaGenerateRequest captchaGenerateRequest = new CaptchaGenerateRequest();
        captchaGenerateRequest.setRequestId(request.getDeviceId());
        try {
            ValidateCode validateCode = smsCodeGenerator.create(captchaGenerateRequest);
            crawlerCaptchaRespDTO.setSuccess(true);
            log.info("向手机号: {}发送短信验证码: {}", request.getMobile(), validateCode.getCode());
        } catch (ValidateCodeException e) {
            log.error(e.getMessage(), e);
            throw new ControllerException(e.getMessage());
        }
        return Result.succeed(crawlerCaptchaRespDTO);
    }

    private SysCaptchaRespDTO convert(ImageValidateCode imageValidateCode) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(imageValidateCode.getImage(), "JPEG", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String base64ImgCode = Base64Utils.encodeToString(bytes);
        SysCaptchaRespDTO openJobCaptchaRespDTO = new SysCaptchaRespDTO();
        openJobCaptchaRespDTO.setImageCode(base64ImgCode);
        openJobCaptchaRespDTO.setSuccess(true);
        return openJobCaptchaRespDTO;
    }
}
