package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saucesubfresh.admin.server.dto.req.SysAccessReqDTO;
import com.saucesubfresh.admin.server.entity.SysAccessDO;
import com.saucesubfresh.admin.server.entity.SysAccessDO;
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
public interface SysAccessMapper extends BaseMapper<SysAccessDO> {

  default Page<SysAccessDO> queryPage(SysAccessReqDTO sysAccessReqDTO) {
    return selectPage(sysAccessReqDTO.page(), Wrappers.<SysAccessDO>lambdaQuery()
        .eq(StringUtils.isNotEmpty(sysAccessReqDTO.getName()), SysAccessDO::getName, sysAccessReqDTO.getName())
        .eq(StringUtils.isNotEmpty(sysAccessReqDTO.getPath()), SysAccessDO::getPath, sysAccessReqDTO.getPath())
    );
  }

  default List<SysAccessDO> queryList() {
    return selectList(Wrappers.<SysAccessDO>lambdaQuery()
        .orderByDesc(SysAccessDO::getCreateTime));
  }
}
