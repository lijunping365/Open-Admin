package com.pro.admin.common.vo;

/**
 * 结果枚举
 * 全局错误码，占用 [0, 999]
 * 业务异常错误码，占用 [1 000 000 000, +∞)
 *
 * @author lijunping
 */
public enum ResultEnum {

  SUCCESS(200, "成功"),

  BAD_REQUEST(400, "请求参数不正确"),

  UNAUTHORIZED(401, "账号未登录"),

  FORBIDDEN(403, "没有该操作权限"),

  NOT_FOUND(404, "请求未找到"),

  METHOD_NOT_ALLOWED(405, "请求方法不正确"),

  ERROR(500, "系统异常"),

  UNKNOWN(999, "未知错误"),

  DEGRADE_EXCEPTION(1000, "服务繁忙，请稍后再试"),

  USERNAME_OR_PASSWORD_ERROR(1001, "用户名或密码错误"),

  CAPTCHA_ERROR(1002, "验证码输入错误"),

  ACCOUNT_LOCKED(1003, "账户已被锁定"),
  ;

  private final Integer code;

  private final String msg;

  ResultEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

}
