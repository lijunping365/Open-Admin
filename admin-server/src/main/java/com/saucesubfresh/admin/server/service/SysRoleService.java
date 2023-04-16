package com.saucesubfresh.admin.server.service;


import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.dto.create.SysRoleCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysRoleReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleUpdateDTO;

import java.util.List;
import java.util.Set;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:20:48
 */
public interface SysRoleService {

  void save(SysRoleCreateDTO sysRoleCreateDTO);

  void update(SysRoleUpdateDTO sysRoleUpdateDTO);

  void delete(Long id);

  PageResult<SysRoleRespDTO> selectPage(SysRoleReqDTO sysRoleReqDTO);

  List<SysRoleRespDTO> selectAll();

  void allocMenu(SysRoleUpdateDTO sysRoleUpdateDTO);

  void allocAccess(SysRoleUpdateDTO sysRoleUpdateDTO);
}

