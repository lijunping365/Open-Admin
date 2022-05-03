package com.saucesubfresh.admin.server.dto.create;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysRoleMenuCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   *
   */
  private Long id;
  /**
   *
   */
  private Long roleId;
  /**
   *
   */
  private Long menuId;

  /**
   * 权限，逗号分隔字符串
   */
  private String authorities;
}
