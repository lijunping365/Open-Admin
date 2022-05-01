package com.saucesubfresh.admin.server.service;


import com.saucesubfresh.admin.server.dto.resp.SysResourceRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleResourceUpdateDTO;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
public interface SysRoleResourceService {

  /**
   * 获取指定角色的菜单列表
   *
   * @param roleId
   * @return
   */
  List<SysResourceRespDTO> listResource(Long roleId);

  /**
   * 分配权限
   *
   * @return
   */
  boolean allocResource(SysRoleResourceUpdateDTO sysRoleResourceUpdateDTO);

}

