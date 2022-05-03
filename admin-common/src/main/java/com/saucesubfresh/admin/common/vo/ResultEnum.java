package com.saucesubfresh.admin.common.vo;

/**
 * 结果枚举
 * 全局错误码，占用 [0, 999]
 * 业务异常错误码，占用 [1 000 000 000, +∞)
 *
 * @author lijunping
 */
public enum ResultEnum {

  SUCCESS(200, "成功"),

  ERROR(1000, "系统异常"),
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
