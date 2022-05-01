package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
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

  default Page<SysMenuDO> queryPage(SysMenuReqDTO sysMenuReqDTO) {
    return selectPage(sysMenuReqDTO.page(), Wrappers.<SysMenuDO>lambdaQuery()
        .like(sysMenuReqDTO.getName() != null, SysMenuDO::getName, sysMenuReqDTO.getName()));
  }

  default List<SysMenuDO> queryList(Integer total) {
    return selectList(Wrappers.<SysMenuDO>lambdaQuery()
        .eq(total != null, SysMenuDO::getEnableStatus, CommonStatusEnum.of(total))
        .orderByAsc(SysMenuDO::getSort));
  }
}
