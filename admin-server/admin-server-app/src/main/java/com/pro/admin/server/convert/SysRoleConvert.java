package com.pro.admin.server.convert;

import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.create.SysRoleCreateDTO;
import com.pro.admin.server.dto.resp.SysRoleRespDTO;
import com.pro.admin.server.dto.update.SysRoleUpdateDTO;
import com.pro.admin.server.entity.SysRoleDO;
import com.pro.admin.server.vo.SysRoleVO;
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

  List<SysRoleVO> convertList2(List<SysRoleDO> roleDOS);
}


