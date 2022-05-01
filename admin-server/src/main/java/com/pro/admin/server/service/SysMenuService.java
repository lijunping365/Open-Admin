package com.pro.admin.server.service;



import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.dto.create.SysMenuCreateDTO;
import com.pro.admin.server.dto.req.SysMenuReqDTO;
import com.pro.admin.server.dto.resp.SysMenuRespDTO;
import com.pro.admin.server.dto.update.SysMenuUpdateDTO;
import com.pro.admin.server.vo.MenuTreeVO;

import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:20:48
 */
public interface SysMenuService {

  List<MenuTreeVO> getMenuTree(Long userId);

  PageResult<SysMenuRespDTO> selectPage(SysMenuReqDTO sysMenuReqDTO);

  SysMenuRespDTO getItem(Long id);

  boolean save(SysMenuCreateDTO sysMenuCreateDTO);

  boolean update(SysMenuUpdateDTO sysMenuUpdateDTO);

  boolean delete(Long id);

  List<SysMenuRespDTO> treeList(Integer total);
}

