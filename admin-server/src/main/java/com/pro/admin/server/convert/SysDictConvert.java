package com.pro.admin.server.convert;


import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.resp.SysDictRespDTO;
import com.pro.admin.server.dto.update.SysDictUpdateDTO;
import com.pro.admin.server.entity.SysDictDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-05 14:00:49
 */
@Mapper(uses = {
    CommonStatusEnum.Converter.class
})
public interface SysDictConvert {

  SysDictConvert INSTANCE = Mappers.getMapper(SysDictConvert.class);

  SysDictRespDTO convert(SysDictDO sysDictDO);

  SysDictDO convert(SysDictCreateDTO sysDictCreateDTO);

  SysDictDO convert(SysDictUpdateDTO sysDictUpdateDTO);

  List<SysDictRespDTO> convertList(List<SysDictDO> sysDictDOS);
}


