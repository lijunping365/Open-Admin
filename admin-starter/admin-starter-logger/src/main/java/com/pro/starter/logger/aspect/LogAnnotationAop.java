package com.pro.starter.logger.aspect;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.URLUtil;
import com.pro.admin.common.json.JSON;
import com.pro.starter.logger.annotation.Logger;
import com.pro.starter.logger.model.LogModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 日志注解的处理器
 *
 * @author li
 */
@Slf4j
@Aspect
@Order(-1)
@RequiredArgsConstructor
public class LogAnnotationAop {

  private final ApplicationEventPublisher publisher;


  @Around("@annotation(logger)")
  public Object saveLog(ProceedingJoinPoint joinPoint, Logger logger) throws Throwable {
    Signature signature = joinPoint.getSignature();
    String strClassName = joinPoint.getTarget().getClass().getName();
    String strMethodName = signature.getName();
    log.info("[日志类型：{},][类名]:{},[方法]:{}", logger.type().getName(), strClassName, strMethodName);

    // 获取日志
    LogModel logModel = prodLogModel();
    // 记录参数
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    logModel.setQueryString(getParams(joinPoint, methodSignature));
    if (method.isAnnotationPresent(ApiOperation.class)) {
      ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
      logModel.setDescription(apiOperation.value());
    }
    // 开始时间
    long startTime = System.currentTimeMillis();
    logModel.setStartTime(LocalDateTimeUtil.toLocalDateTime(startTime));
    Object result;
    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      logModel.setStatus(SuccessStatusEnum.FAILED.getValue());
      throw throwable;
    }
    // 结束时间
    logModel.setResponseTime((int) (System.currentTimeMillis() - startTime));
    // 发布事件
    publisher.publishEvent(new LogEvent(logModel, logAnnotation.type()));

    return result;
  }


  /**
   * 获取方法参数
   *
   * @param joinPoint       joinPoint
   * @param methodSignature 方法签名
   * @return 方法参数的Json字符串形式
   */
  private String getParams(ProceedingJoinPoint joinPoint, MethodSignature methodSignature) {
    String[] parameterNames = methodSignature.getParameterNames();
    Object[] args = joinPoint.getArgs();
    if (parameterNames == null || parameterNames.length == 0) {
      return null;
    }
    Map<String, Object> paramsMap = new HashMap<>();
    for (int i = 0; i < parameterNames.length; i++) {
      paramsMap.put(parameterNames[i], args[i]);
    }
    return JSON.toJSON(paramsMap);
  }

  /**
   * 根据请求生成操作日志
   *
   * @return 操作日志DTO
   */
  private LogModel prodLogModel() {
    HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    return new LogModel()
        .setUserId(UserUtil.getUserId())
        .setUserAgent(UserUtil.handlerPlatformType().getValue())
        .setTraceId(TraceUtil.getTraceId())
        .setApplicationName(applicationName)
        .setUri(URLUtil.getPath(request.getRequestURI()))
        .setIp(IpUtil.getIpWithServlet(request))
        .setMethod(request.getMethod())
        .setCreateTime(LocalDateTime.now())
        .setStatus(SuccessStatusEnum.SUCCEED.getValue());
  }

}
