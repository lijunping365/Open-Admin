package com.saucesubfresh.admin.server.convert;

import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:33:05
 */
@Mapper
public interface SysUserConvert {

  SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

  SysUserRespDTO convert(SysUserDO sysUserDO);

  SysUserDO convert(SysUserCreateDTO sysUserCreateDTO);

  SysUserDO convert(SysUserUpdateDTO sysUserUpdateDTO);
}


