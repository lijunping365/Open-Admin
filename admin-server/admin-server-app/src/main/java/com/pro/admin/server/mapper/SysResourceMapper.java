package com.pro.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.req.SysResourceReqDTO;
import com.pro.admin.server.entity.SysResourceDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@Repository
public interface SysResourceMapper extends BaseMapper<SysResourceDO> {

  default Page<SysResourceDO> queryPage(SysResourceReqDTO sysResourceReqDTO) {
    return selectPage(sysResourceReqDTO.page(), Wrappers.<SysResourceDO>lambdaQuery()
        .like(StringUtils.isNotBlank(sysResourceReqDTO.getName()), SysResourceDO::getName, sysResourceReqDTO.getName()));
  }

  default List<SysResourceDO> queryList(Integer total) {
    return selectList(Wrappers.<SysResourceDO>lambdaQuery()
        .eq(total != null, SysResourceDO::getEnableStatus, CommonStatusEnum.of(total)));
  }
}
