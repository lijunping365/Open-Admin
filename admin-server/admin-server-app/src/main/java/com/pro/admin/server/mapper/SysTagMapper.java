package com.pro.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.req.SysTagReqDTO;
import com.pro.admin.server.entity.SysTagDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-12-12 10:52:26
 */
@Repository
public interface SysTagMapper extends BaseMapper<SysTagDO> {

  default Page<SysTagDO> queryPage(SysTagReqDTO sysTagReqDTO) {
    return selectPage(sysTagReqDTO.page(), Wrappers.<SysTagDO>lambdaQuery()
        .like(StringUtils.isNotBlank(sysTagReqDTO.getName()), SysTagDO::getName, sysTagReqDTO.getName())
        .eq(sysTagReqDTO.getEnableStatus() != null, SysTagDO::getEnableStatus, sysTagReqDTO.getEnableStatus())
        .eq(SysTagDO::getDeleteStatus, CommonStatusEnum.YES)
        .eq(SysTagDO::getGroupId, sysTagReqDTO.getGroupId())
        .between(sysTagReqDTO.getBeginTime() != null, SysTagDO::getCreateTime, sysTagReqDTO.getBeginTime(), sysTagReqDTO.getEndTime())
        .orderByAsc(SysTagDO::getSort));
  }

  default List<SysTagDO> queryList(Integer tagType) {
    return selectList(Wrappers.<SysTagDO>lambdaQuery()
        .eq(SysTagDO::getEnableStatus, CommonStatusEnum.YES)
        .eq(SysTagDO::getTagType, SysTagTypeEnum.of(tagType))
        .eq(SysTagDO::getDeleteStatus, CommonStatusEnum.YES));
  }
}
