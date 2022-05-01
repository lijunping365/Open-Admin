package com.saucesubfresh.admin.server.convert;


import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import com.saucesubfresh.admin.server.dto.create.SysRegionCreateDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRegionRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRegionUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysRegionDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 行政区划表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-03 17:14:00
 */
@Mapper(uses = {
        CommonStatusEnum.Converter.class
})
public interface SysRegionConvert {

  SysRegionConvert INSTANCE = Mappers.getMapper(SysRegionConvert.class);

  SysRegionRespDTO convert(SysRegionDO sysRegionDO);

  SysRegionDO convert(SysRegionCreateDTO sysRegionCreateDTO);

  SysRegionDO convert(SysRegionUpdateDTO sysRegionUpdateDTO);

  List<SysRegionRespDTO> convertList(List<SysRegionDO> queryList);
}


