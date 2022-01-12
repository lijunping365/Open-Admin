package com.pro.admin.server.dto.req;


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
public class SysUserRoleReqDTO implements Serializable {
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
