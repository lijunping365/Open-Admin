package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import com.saucesubfresh.admin.server.dto.req.SysRoleReqDTO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {

  default Page<SysRoleDO> queryPage(SysRoleReqDTO sysRoleReqDTO) {
    return selectPage(sysRoleReqDTO.page(), Wrappers.<SysRoleDO>lambdaQuery()
        .eq(StringUtils.isNotEmpty(sysRoleReqDTO.getKeyword()), SysRoleDO::getName, sysRoleReqDTO.getKeyword()));
  }

  default List<SysRoleDO> queryList(Integer total) {
    return selectList(Wrappers.<SysRoleDO>lambdaQuery()
        .eq(total != null, SysRoleDO::getEnableStatus, CommonStatusEnum.of(total))
        .orderByAsc(SysRoleDO::getSort));
  }
}
