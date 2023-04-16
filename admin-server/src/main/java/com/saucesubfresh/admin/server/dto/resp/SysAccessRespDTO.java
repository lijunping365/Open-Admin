package com.saucesubfresh.admin.server.dto.resp;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysAccessRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 权限名称
   */
  private String name;
  /**
   * 请求路径
   */
  private String path;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 修改时间
   */
  private LocalDateTime updateTime;
  /**
   * 创建用户
   */
  private Long createUser;
  /**
   * 修改用户
   */
  private Long updateUser;

}
