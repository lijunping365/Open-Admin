package com.saucesubfresh.admin.server.convert;

import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import com.saucesubfresh.admin.server.dto.create.SysRoleCreateDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:33:05
 */
@Mapper(uses = {
        CommonStatusEnum.Converter.class
})
public interface SysRoleConvert {

  SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

  SysRoleRespDTO convert(SysRoleDO sysRoleDO);

  SysRoleDO convert(SysRoleCreateDTO sysRoleCreateDTO);

  SysRoleDO convert(SysRoleUpdateDTO sysRoleUpdateDTO);

  List<SysRoleRespDTO> convertList(List<SysRoleDO> sysRoleDOList);
}


