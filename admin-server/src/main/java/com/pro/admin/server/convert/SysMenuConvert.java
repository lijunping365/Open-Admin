package com.pro.admin.server.convert;


import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.server.dto.create.SysMenuCreateDTO;
import com.pro.admin.server.dto.resp.SysMenuRespDTO;
import com.pro.admin.server.dto.update.SysMenuUpdateDTO;
import com.pro.admin.server.entity.SysMenuDO;
import com.pro.admin.server.vo.MenuTreeVO;
import com.pro.admin.server.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:33:05
 */
@Mapper(uses = {
        CommonStatusEnum.Converter.class
})
public interface SysMenuConvert {

  SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

  SysMenuRespDTO convert(SysMenuDO sysMenuDO);

  SysMenuVO convertVO(SysMenuDO sysMenuDO);

  SysMenuDO convert(SysMenuCreateDTO sysMenuCreateDTO);

  SysMenuDO convert(SysMenuUpdateDTO sysMenuUpdateDTO);

  List<SysMenuRespDTO> convertList(List<SysMenuDO> sysMenuDOList);

  List<MenuTreeVO> convertListVO(List<SysMenuDO> menuList);

  List<SysMenuVO> convertList2(List<SysMenuDO> menuDOS);
}


