package com.pro.starter.captcha.core.image;


import com.google.code.kaptcha.Producer;
import com.pro.starter.captcha.exception.ValidateCodeException;
import com.pro.starter.captcha.processor.AbstractValidateCodeProcessor;
import com.pro.starter.captcha.properties.CaptchaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description:
 * (1)配置文件中可以配置高度、宽度、验证码的位数和过期时间
 */
@Slf4j
@Component
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCaptchaRequest, ImageValidateCode> {

  private final CaptchaProperties captchaProperties;

  private final Producer producer;

  public ImageCodeProcessor(CaptchaProperties captchaProperties, Producer producer) {
    this.captchaProperties = captchaProperties;
    this.producer = producer;
  }

  @Override
  public ImageValidateCode generate() {
    String text = producer.createText();
    BufferedImage image = producer.createImage(text);
    return new ImageValidateCode(image, text, captchaProperties.getCode().getImage().getExpireTime());
  }

  @Override
  protected void send(ImageCaptchaRequest request, ImageValidateCode code) {
    assert response != null;
    try {
      ImageIO.write(code.getImage(), "JPEG", response.getOutputStream());
    } catch (IOException e) {
      log.error("[发送图片验证码失败]:{}", e.getMessage());
      throw new ValidateCodeException("发送图片验证码失败");
    }
  }
}
