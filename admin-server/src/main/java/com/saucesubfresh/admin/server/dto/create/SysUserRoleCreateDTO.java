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
public class SysUserRoleCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   *
   */
  private Long id;
  /**
   *
   */
  private Long userId;
  /**
   *
   */
  private Long roleId;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 创建人
   */
  private Long createUser;

}
