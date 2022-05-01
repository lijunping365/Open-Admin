package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.entity.SysRoleMenuDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuDO> {

  List<SysMenuDO> queryList(Long roleId);

  default void deleteRoleMenu(Long roleId, Long menuId) {
    delete(Wrappers.<SysRoleMenuDO>lambdaQuery()
        .eq(SysRoleMenuDO::getRoleId, roleId)
        .eq(SysRoleMenuDO::getMenuId, menuId));
  }

  List<SysMenuDO> loadRoleMenusByRoleId(Long roleId);
}
