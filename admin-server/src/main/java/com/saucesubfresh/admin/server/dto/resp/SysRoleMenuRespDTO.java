package com.saucesubfresh.admin.server.dto.resp;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysRoleMenuRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 菜单角色 map
   */
  private Map<String, List<String>> resourceRolesMap;
}
