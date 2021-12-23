package com.pro.starter.captcha.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lijunping on 2021/12/22
 */
@Data
public class BaseCaptchaRequest implements Serializable {

    @NotBlank(message = "设备唯一 id 不能为空")
    @ApiModelProperty("设备唯一 id")
    private String deviceId;
}
