package com.pro.admin.server.dto.resp;


import lombok.Data;

import java.io.Serializable;

/**
 * 
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysUserRoleRespDTO implements Serializable {
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

}
