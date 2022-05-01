package com.pro.admin.server.convert;


import com.pro.admin.server.dto.resp.SysResourceRespDTO;
import com.pro.admin.server.dto.resp.SysRoleResourceRespDTO;
import com.pro.admin.server.entity.SysRoleResourceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
@Mapper
public interface SysRoleResourceConvert {

  SysRoleResourceConvert INSTANCE = Mappers.getMapper(SysRoleResourceConvert.class);

  SysRoleResourceRespDTO convert(SysRoleResourceDO sysRoleResourceDO);

  List<SysResourceRespDTO> convertList(List<SysRoleResourceDO> resourceDOS);
}


