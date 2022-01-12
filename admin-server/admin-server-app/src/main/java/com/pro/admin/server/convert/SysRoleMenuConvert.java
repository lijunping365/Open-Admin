package com.pro.admin.server.convert;

import com.pro.admin.server.dto.resp.SysRoleMenuRespDTO;
import com.pro.admin.server.entity.SysRoleMenuDO;
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
public interface SysRoleMenuConvert {

  SysRoleMenuConvert INSTANCE = Mappers.getMapper(SysRoleMenuConvert.class);

  SysRoleMenuRespDTO convert(SysRoleMenuDO sysRoleMenuDO);

}


