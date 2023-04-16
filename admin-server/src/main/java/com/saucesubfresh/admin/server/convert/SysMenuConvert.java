package com.saucesubfresh.admin.server.convert;


import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import com.saucesubfresh.admin.server.dto.create.SysMenuCreateDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysMenuUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.vo.MenuTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

  SysMenuDO convert(SysMenuCreateDTO sysMenuCreateDTO);

  SysMenuDO convert(SysMenuUpdateDTO sysMenuUpdateDTO);

  List<SysMenuRespDTO> convertList(List<SysMenuDO> sysMenuDOList);

  @Mapping(target = "path", source = "route")
  MenuTreeVO convertMenuTree(SysMenuDO sysMenuDO);

  List<MenuTreeVO> convertListVO(List<SysMenuDO> menuList);
}


