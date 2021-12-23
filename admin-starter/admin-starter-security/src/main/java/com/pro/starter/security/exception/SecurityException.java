package com.pro.starter.security.exception;

import com.pro.admin.common.vo.ResultEnum;
import lombok.Data;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 认证异常类
 */
@Data
public class SecurityException extends RuntimeException{

    private Integer code;

    private String message;

    public SecurityException(String msg, Throwable t) {
        super(msg, t);
    }

    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
    }

    public SecurityException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


}
