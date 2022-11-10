package com.saucesubfresh.admin.server.config;

import com.saucesubfresh.admin.common.exception.ServiceException;
import com.saucesubfresh.admin.common.vo.Result;
import com.saucesubfresh.starter.security.exception.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器，将 Exception 翻译成 CommonResult + 对应的异常编号
 *
 * @author lijunping
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 处理 SpringMVC 404 异常
   */
  @ExceptionHandler({NoHandlerFoundException.class})
  public Result<Object> badRequest(NoHandlerFoundException ex) {
    log.warn("[noHandlerFoundExceptionHandler]", ex);
    return Result.failed(String.format("请求的页面不存在:%s", ex.getRequestURL()));
  }

  /**
   * 处理 SpringMVC 请求参数缺失
   * <p>
   * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
   */
  @ExceptionHandler({MissingServletRequestParameterException.class})
  public Result<Object> handleError(MissingServletRequestParameterException ex) {
    log.warn("[missingServletRequestParameterExceptionHandler]", ex);
    return Result.failed(String.format("请求参数缺失:%s", ex.getParameterName()));
  }

  /**
   * 处理 SpringMVC 请求参数类型错误
   * <p>
   * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
   */
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public Result<Object> handleError(MethodArgumentTypeMismatchException ex) {
    log.warn("[missingServletRequestParameterExceptionHandler]", ex);
    return Result.failed(String.format("请求参数类型错误:%s", ex.getParameter().getParameterName()));
  }

  /**
   * 处理 SpringMVC 参数校验不正确，使用 （Validator（@NotNull...） + 自动抛出异常） 可以省略 BindingResult 这一步
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result<Object> methodArgumentNotValid(MethodArgumentNotValidException ex) {
    log.warn("[methodArgumentNotValidExceptionHandler]", ex);
    return Result.failed(String.format("请求参数不正确:%s", ex.getMessage()));
  }

  /**
   * 处理 SpringMVC 请求方法不正确
   * <p>
   * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public Result<Object> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
    log.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
    return Result.failed(String.format("请求方法不正确:%s", ex.getMessage()));
  }

  @ExceptionHandler({SecurityException.class, RuntimeException.class, Exception.class})
  public Result<Object> securityException(SecurityException ex) {
    log.warn("[securityException]", ex);
    return Result.failed(ex.getMessage());
  }

  @ExceptionHandler({ServiceException.class})
  public Result<Object> serviceException(ServiceException ex) {
    log.warn("[serviceExceptionHandler]", ex);
    return Result.failed(ex.getCode(), ex.getMessage());
  }
}
