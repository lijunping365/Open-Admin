package com.saucesubfresh.admin.server.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: 李俊平
 * @Date: 2020-11-04 16:32
 */
@Data
public abstract class BaseDO {

  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 修改时间
   */
  private LocalDateTime updateTime;
  /**
   * 创建人
   */
  private Long createUser;
  /**
   * 修改人
   */
  private Long updateUser;
}
