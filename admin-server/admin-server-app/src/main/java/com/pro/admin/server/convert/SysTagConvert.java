package com.pro.admin.server.convert;


import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.create.SysTagCreateDTO;
import com.pro.admin.server.dto.resp.SysTagRespDTO;
import com.pro.admin.server.dto.update.SysTagUpdateDTO;
import com.pro.admin.server.entity.SysTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-12-12 10:52:26
 */
@Mapper(uses = {
    CommonStatusEnum.Converter.class,
})
public interface SysTagConvert {

  SysTagConvert INSTANCE = Mappers.getMapper(SysTagConvert.class);

  SysTagRespDTO convert(SysTagDO productTagDO);

  SysTagDO convert(SysTagCreateDTO productTagCreateDTO);

  SysTagDO convert(SysTagUpdateDTO productTagUpdateDTO);

  List<SysTagRespDTO> convertList(List<SysTagDO> productTagDOS);
}


