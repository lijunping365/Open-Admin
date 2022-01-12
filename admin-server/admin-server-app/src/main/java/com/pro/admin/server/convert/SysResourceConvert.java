package com.pro.admin.server.convert;


import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.create.SysResourceCreateDTO;
import com.pro.admin.server.dto.resp.SysResourceRespDTO;
import com.pro.admin.server.dto.update.SysResourceUpdateDTO;
import com.pro.admin.server.entity.SysResourceDO;
import com.pro.admin.server.vo.SysResourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@Mapper(uses = {
        CommonStatusEnum.Converter.class
})
public interface SysResourceConvert {

  SysResourceConvert INSTANCE = Mappers.getMapper(SysResourceConvert.class);

  SysResourceRespDTO convert(SysResourceDO sysResourceDO);

  SysResourceDO convert(SysResourceCreateDTO sysResourceCreateDTO);

  SysResourceDO convert(SysResourceUpdateDTO sysResourceUpdateDTO);

  List<SysResourceVO> convertList(List<SysResourceDO> resourceDOS);

  List<SysResourceRespDTO> convertList2(List<SysResourceDO> sysResourceDOS);
}


