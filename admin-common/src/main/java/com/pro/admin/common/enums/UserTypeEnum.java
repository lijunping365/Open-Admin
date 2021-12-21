package com.pro.admin.common.enums;

import com.pro.admin.common.exception.BaseException;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author 李俊平
 * @date 2020-10-12 20:36
 */
@Getter
public enum UserTypeEnum {

  BUSINESS,

  CLIENT,

  MANAGEMENT,

  DEVELOPER,

  ;

  public static UserTypeEnum getUserType(String userType) {
    return Arrays.stream(UserTypeEnum.values())
        .filter(userTypeEnum -> userTypeEnum.name().toLowerCase().equals(userType))
        .findFirst().orElseThrow(() -> new BaseException(String.format("枚举查找失败:%s", userType)));
  }
}
