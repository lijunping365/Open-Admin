package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.server.convert.SysMenuConvert;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleMenuUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.entity.SysRoleMenuDO;
import com.saucesubfresh.admin.server.mapper.SysRoleMenuMapper;
import com.saucesubfresh.admin.server.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements SysRoleMenuService{

  @Autowired
  private SysRoleMenuMapper sysRoleMenuMapper;

  @Override
  public List<SysMenuRespDTO> listMenu(Long roleId) {
    List<SysMenuDO> sysMenuDOList = sysRoleMenuMapper.queryList(roleId);
    return SysMenuConvert.INSTANCE.convertList(sysMenuDOList);
  }

  @Override
  public boolean allocMenu(SysRoleMenuUpdateDTO sysRoleMenuUpdateDTO) {
    //先删除原有关系
    List<SysMenuRespDTO> sysMenuRespDTOS = this.listMenu(sysRoleMenuUpdateDTO.getRoleId());
    if (!sysMenuRespDTOS.isEmpty()) {
      for (SysMenuRespDTO sysMenuRespDTO : sysMenuRespDTOS) {
        sysRoleMenuMapper.deleteRoleMenu(sysRoleMenuUpdateDTO.getRoleId(), sysMenuRespDTO.getId());
      }
    }
    //插入新关系
    for (Long menuId : sysRoleMenuUpdateDTO.getMenuId()) {
      SysRoleMenuDO sysRoleMenuDO = new SysRoleMenuDO();
      sysRoleMenuDO.setRoleId(sysRoleMenuUpdateDTO.getRoleId());
      sysRoleMenuDO.setMenuId(menuId);
      sysRoleMenuMapper.insert(sysRoleMenuDO);
    }
    return true;
  }

  @Override
  public Set<String> getAuthorities(List<String> roles) {
    return null;
  }
}