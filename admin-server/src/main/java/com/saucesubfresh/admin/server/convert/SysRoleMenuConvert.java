package com.saucesubfresh.admin.server.convert;

import com.saucesubfresh.admin.server.dto.resp.SysRoleMenuRespDTO;
import com.saucesubfresh.admin.server.entity.SysRoleMenuDO;
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


