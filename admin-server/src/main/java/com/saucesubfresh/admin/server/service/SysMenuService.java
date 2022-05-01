package com.saucesubfresh.admin.server.service;



import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.dto.create.SysMenuCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysMenuReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysMenuUpdateDTO;
import com.saucesubfresh.admin.server.vo.MenuTreeVO;

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

