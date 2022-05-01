package com.pro.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.admin.server.convert.SysMenuConvert;
import com.pro.admin.server.dto.resp.SysMenuRespDTO;
import com.pro.admin.server.dto.update.SysRoleMenuUpdateDTO;
import com.pro.admin.server.entity.SysMenuDO;
import com.pro.admin.server.entity.SysRoleMenuDO;
import com.pro.admin.server.mapper.SysRoleMenuMapper;
import com.pro.admin.server.service.SysRoleMenuService;
import com.pro.starter.security.annotation.PreAuthorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pro.admin.server.constant.CacheName.MENU_TREE;


@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements SysRoleMenuService {

  @Autowired
  private SysRoleMenuMapper sysRoleMenuMapper;

  @Override
  public List<SysMenuRespDTO> listMenu(Long roleId) {
    List<SysMenuDO> sysMenuDOList = sysRoleMenuMapper.queryList(roleId);
    return SysMenuConvert.INSTANCE.convertList(sysMenuDOList);
  }

  @PreAuthorization
  @CacheEvict(value = MENU_TREE, key = "'getMenuTree'")
  @Override
  @Transactional
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


}