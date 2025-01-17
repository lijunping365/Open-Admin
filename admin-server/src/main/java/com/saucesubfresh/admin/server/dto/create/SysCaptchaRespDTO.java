package com.saucesubfresh.admin.server.dto.create;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 李俊平
 * @Date: 2022-05-08 12:43
 */
@Data
public class SysCaptchaRespDTO implements Serializable {

    /**
     * 生成是否成功
     */
    private Boolean success = false;

    /**
     * 生成的图片验证码， base64 字符串
     */
    private String imageCode;
}
