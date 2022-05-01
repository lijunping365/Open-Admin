package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.entity.SysUserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUserDO> {

  default SysUserDO loadUserByUsername(String username) {
    return selectOne(Wrappers.lambdaQuery(SysUserDO.class).eq(SysUserDO::getUsername, username));
  }

  default SysUserDO loadUserByMobile(String mobile) {
    return selectOne(Wrappers.lambdaQuery(SysUserDO.class).eq(SysUserDO::getMobile, mobile));
  }

  default SysUserDO loadUserByUserId(Long userId) {
    return selectById(userId);
  }

  default void saveUser(SysUserDO sysUserDO) {
    insert(sysUserDO);
  }

  default Page<SysUserDO> queryPage(SysUserReqDTO sysUserReqDTO) {
    return selectPage(sysUserReqDTO.page(), Wrappers.<SysUserDO>lambdaQuery()
        .like(StringUtils.isNotEmpty(sysUserReqDTO.getUsername()), SysUserDO::getUsername, sysUserReqDTO.getUsername())
        .like(StringUtils.isNotEmpty(sysUserReqDTO.getMobile()), SysUserDO::getMobile, sysUserReqDTO.getMobile())
        .eq(StringUtils.isNotEmpty(sysUserReqDTO.getSex()), SysUserDO::getSex, sysUserReqDTO.getSex())
        .eq(sysUserReqDTO.getAccountStatus() != null, SysUserDO::getAccountStatus, sysUserReqDTO.getAccountStatus())
        .between(sysUserReqDTO.getBeginTime() != null, SysUserDO::getCreateTime, sysUserReqDTO.getBeginTime(), sysUserReqDTO.getEndTime()));
  }
}
