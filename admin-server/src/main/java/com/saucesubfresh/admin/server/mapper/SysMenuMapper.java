package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saucesubfresh.admin.server.dto.req.SysMenuReqDTO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenuDO> {

  List<SysMenuDO> getMenuList(Long userId);

  default List<SysMenuDO> queryList(SysMenuReqDTO sysMenuReqDTO) {
    return selectList(Wrappers.<SysMenuDO>lambdaQuery()
        .like(sysMenuReqDTO.getName() != null, SysMenuDO::getName, sysMenuReqDTO.getName()));
  }
}
