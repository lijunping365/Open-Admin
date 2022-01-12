package com.pro.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pro.admin.server.entity.SysResourceDO;
import com.pro.admin.server.entity.SysRoleResourceDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
@Repository
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResourceDO> {

  List<Map<String, String>> loadResourceRolesMap();

  default List<SysRoleResourceDO> queryList(Long roleId) {
    return selectList(Wrappers.<SysRoleResourceDO>lambdaQuery().eq(SysRoleResourceDO::getRoleId, roleId));
  }

  default void deleteRoleResource(Long roleId, Long resourceId) {
    delete(Wrappers.<SysRoleResourceDO>lambdaQuery()
        .eq(SysRoleResourceDO::getRoleId, roleId)
        .eq(SysRoleResourceDO::getResourceId, resourceId));
  }

  List<SysResourceDO> loadRoleResourcesByRoleId(Long roleId);
}
