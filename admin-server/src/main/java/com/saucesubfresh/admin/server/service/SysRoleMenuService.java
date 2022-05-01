package com.saucesubfresh.admin.server.service;


import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleMenuUpdateDTO;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:20:48
 */
public interface SysRoleMenuService {

  /**
   * 获取指定角色的菜单列表
   *
   * @param roleId
   * @return
   */
  List<SysMenuRespDTO> listMenu(Long roleId);

  /**
   * 分配权限
   *
   * @return
   */
  boolean allocMenu(SysRoleMenuUpdateDTO sysRoleMenuUpdateDTO);
}

