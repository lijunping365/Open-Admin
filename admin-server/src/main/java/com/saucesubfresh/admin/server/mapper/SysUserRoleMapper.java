package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import com.saucesubfresh.admin.server.entity.SysUserRoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleDO> {

  List<SysRoleDO> loadUserRolesByUserId(Long userId);

  default void deleteUserRoles(Long userId, Long roleId) {
    delete(Wrappers.<SysUserRoleDO>lambdaQuery()
        .eq(SysUserRoleDO::getUserId, userId)
        .eq(SysUserRoleDO::getRoleId, roleId));
  }
}
