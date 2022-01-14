package com.pro.admin.server.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lijunping on 2022/1/14
 */
@Data
public class SysPasswordOAuthLoginReqDTO implements Serializable {
    private static final long serialVersionUID = 6158062619639167676L;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("图片验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
