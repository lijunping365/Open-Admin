package com.pro.admin.server.convert;

import com.pro.admin.server.dto.resp.SysUserRoleRespDTO;
import com.pro.admin.server.entity.SysUserRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:33:05
 */
@Mapper
public interface SysUserRoleConvert {

  SysUserRoleConvert INSTANCE = Mappers.getMapper(SysUserRoleConvert.class);

  SysUserRoleRespDTO convert(SysUserRoleDO sysUserRoleDO);

}


