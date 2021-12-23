package com.pro.starter.captcha.processor;

import com.pro.starter.captcha.core.domain.ValidateCode;
import com.pro.starter.captcha.exception.ValidateCodeException;
import com.pro.starter.captcha.repository.ValidateCodeRepository;
import com.pro.starter.captcha.request.BaseCaptchaRequest;
import com.pro.starter.captcha.request.CaptchaVerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 *
 * Description: 验证码抽象处理器，包含验证码的生成处理，保存处理, 验证处理
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor<T extends BaseCaptchaRequest, R extends ValidateCode> implements ValidateCodeProcessor<T> {

  @Autowired
  private ValidateCodeRepository validateCodeRepository;

  @Resource
  protected HttpServletResponse response;

  @Override
  public void process(T request){
    R code;
    try {
      code = generate();
    } catch (Exception e) {
      log.error("[验证码生成失败]:{}", e.getMessage());
      throw new ValidateCodeException("验证码生成失败");
    }
    save(code);
    send(request, code);
  }

  /**
   * 保存校验码
   */
  private void save(R validateCode) {
    ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
    validateCodeRepository.save(code.getCode(), code);
  }

  @Override
  public void validate(CaptchaVerifyRequest request) {
    String codeInRequest = request.getCode();
    // 获取验证码
    ValidateCode validateCode = validateCodeRepository.get(request.getCode());

    if (validateCode == null) {
      throw new ValidateCodeException("验证码不存在");
    }

    // 判断验证码是否过期，过期将验证码移除
    if (validateCode.isExpired()) {
      validateCodeRepository.remove(request.getCode());
      throw new ValidateCodeException("验证码已过期");
    }

    if (!StringUtils.equals(validateCode.getCode(), codeInRequest)) {
      throw new ValidateCodeException("验证码不匹配");
    }

    // 校验成功后将验证码移除
    validateCodeRepository.remove(request.getCode());
  }

  /**
   * 生成校验码
   */
  protected abstract R generate() throws Exception;
  /**
   * 发送验证码
   */
  protected abstract void send(T request, R code);

}
