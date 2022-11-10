package com.saucesubfresh.admin.server.service;


import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;

import java.util.List;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:20:48
 */
public interface SysUserService {

  SysUserRespDTO loadUserByUserId(Long userId);

  void saveUser(SysUserCreateDTO sysUserCreateDTO);

  void update(SysUserUpdateDTO sysUserUpdateDTO);

  PageResult<SysUserRespDTO> selectPage(SysUserReqDTO sysUserReqDTO);

  void allocRole(SysUserUpdateDTO sysUserUpdateDTO);

  List<SysMenuRespDTO> getMenus();
}

