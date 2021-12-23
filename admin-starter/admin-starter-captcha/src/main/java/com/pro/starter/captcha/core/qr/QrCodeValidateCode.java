package com.pro.starter.captcha.core.qr;

import com.pro.starter.captcha.core.domain.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;


/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * 二维码
 */
@Data
public class QrCodeValidateCode extends ValidateCode {

  private static final long serialVersionUID = -8359430591111380080L;
  /**
   * 图片
   */
  private BufferedImage image;

    /**
     * 构造-----expireIn 超时时间（单位秒）
     * @param image
     * @param code
     * @param expireIn
     */
    public QrCodeValidateCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

}
