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

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String code;
}
