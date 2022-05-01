package com.saucesubfresh.admin.server.service;

import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserRoleUpdateDTO;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:20:48
 */
public interface SysUserRoleService {

  List<SysRoleRespDTO> listRoles(Long userId);

  boolean allocRole(SysUserRoleUpdateDTO sysUserRoleUpdateDTO);
}

