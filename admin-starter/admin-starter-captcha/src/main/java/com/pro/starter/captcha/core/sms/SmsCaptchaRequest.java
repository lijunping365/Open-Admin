package com.pro.starter.captcha.core.sms;

import com.pro.starter.captcha.request.BaseCaptchaRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 验证码生成请求实体类
 */
@Data
public class SmsCaptchaRequest extends BaseCaptchaRequest implements Serializable {
    private static final long serialVersionUID = -3596061866163459943L;

    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty("手机号")
    private String mobile;
}
