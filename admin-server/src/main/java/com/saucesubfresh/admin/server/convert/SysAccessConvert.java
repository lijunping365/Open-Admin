package com.saucesubfresh.admin.server.convert;

import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import com.saucesubfresh.admin.server.dto.create.SysAccessCreateDTO;
import com.saucesubfresh.admin.server.dto.resp.SysAccessRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysAccessUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysAccessDO;
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
public interface SysAccessConvert {

  SysAccessConvert INSTANCE = Mappers.getMapper(SysAccessConvert.class);

  SysAccessRespDTO convert(SysAccessDO sysAccessDO);

  SysAccessDO convert(SysAccessCreateDTO sysAccessCreateDTO);

  SysAccessDO convert(SysAccessUpdateDTO sysAccessUpdateDTO);

  List<SysAccessRespDTO> convertList(List<SysAccessDO> sysAccessDOList);
}


