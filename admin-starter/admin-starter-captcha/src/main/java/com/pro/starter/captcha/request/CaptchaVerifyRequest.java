package com.pro.starter.captcha.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description:校验码处理请求实体类
 */
@Data
@NoArgsConstructor
public class CaptchaVerifyRequest implements Serializable {
    private static final long serialVersionUID = -3596061866163459943L;

    @NotBlank(message = "验证码类型不能为空")
    @ApiModelProperty("验证码类型")
    private String type;

    @NotBlank(message = "设备唯一 id 不能为空")
    @ApiModelProperty("设备唯一 id")
    private String deviceId;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("手机号")
    private String mobile;

    public CaptchaVerifyRequest(String type, String deviceId, String code) {
        this.type = type;
        this.deviceId = deviceId;
        this.code = code;
    }

    public CaptchaVerifyRequest(String type, String deviceId, String code, String mobile) {
        this.deviceId = deviceId;
        this.code = code;
        this.mobile = mobile;
    }
}
