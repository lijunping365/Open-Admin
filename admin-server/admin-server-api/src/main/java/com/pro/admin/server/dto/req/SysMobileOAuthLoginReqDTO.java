package com.pro.admin.server.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lijunping on 2022/1/14
 */
@Data
public class SysMobileOAuthLoginReqDTO implements Serializable {

    private static final long serialVersionUID = -8212093482525684659L;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("图片验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
