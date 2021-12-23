package com.pro.starter.captcha.core.qr;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pro.starter.captcha.exception.ValidateCodeException;
import com.pro.starter.captcha.processor.AbstractValidateCodeProcessor;
import com.pro.starter.captcha.properties.CaptchaProperties;
import com.pro.starter.captcha.properties.ScanCodeProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * @Description: 注意验证码+ 超时时间可以进行配置
 */
@Slf4j
@Component
public class QrCodeCodeProcessor extends AbstractValidateCodeProcessor<QrCodeCaptchaRequest, QrCodeValidateCode> {

  @Autowired
  private CaptchaProperties captchaProperties;

  @Resource
  protected HttpServletResponse response;

  @Override
  public QrCodeValidateCode generate() throws WriterException {
    String str = UUID.randomUUID().toString();
    String uuid = str.replaceAll("-", "");
    ScanCodeProperties scanCodeProperties = captchaProperties.getCode().getScan();

    Hashtable<EncodeHintType, Object> hintTypes = new Hashtable<>();

    hintTypes.put(EncodeHintType.CHARACTER_SET, CharacterSetECI.UTF8);//设置编码
    hintTypes.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错级别
    hintTypes.put(EncodeHintType.MARGIN, scanCodeProperties.getMargin());//设置外边距

    BitMatrix bitMatrix = new MultiFormatWriter().encode(uuid, BarcodeFormat.QR_CODE, scanCodeProperties.getWidth(), scanCodeProperties.getHeight(), hintTypes);

    //将二维码写入图片
    BufferedImage bufferedImage = new BufferedImage(scanCodeProperties.getWidth(), scanCodeProperties.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < scanCodeProperties.getWidth(); i++) {
      for (int j = 0; j < scanCodeProperties.getHeight(); j++) {
        bufferedImage.setRGB(i, j, bitMatrix.get(i, j) ? Color.black.getRGB() : Color.WHITE.getRGB());
      }
    }

    return new QrCodeValidateCode(bufferedImage, uuid, scanCodeProperties.getExpireTime());
  }

  @Override
  protected void send(QrCodeCaptchaRequest request, QrCodeValidateCode code) {
    assert response != null;
    try {
      ImageIO.write(code.getImage(), "PNG", response.getOutputStream());
    } catch (IOException e) {
      log.error("[发送二维码失败]:{}", e.getMessage());
      throw new ValidateCodeException("发送二维码失败");
    }
  }

}
