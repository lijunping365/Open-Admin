package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.server.convert.SysMenuConvert;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleMenuUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import com.saucesubfresh.admin.server.entity.SysRoleMenuDO;
import com.saucesubfresh.admin.server.mapper.SysRoleMapper;
import com.saucesubfresh.admin.server.mapper.SysRoleMenuMapper;
import com.saucesubfresh.admin.server.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements SysRoleMenuService{

  @Autowired
  private SysRoleMenuMapper sysRoleMenuMapper;

  @Autowired
  private SysRoleMapper sysRoleMapper;

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
  public Set<String> getAuthorities(List<String> roleNames) {
    List<SysRoleDO> sysRoleDOS = sysRoleMapper.queryListByRoleName(roleNames);
    List<Long> roleIds = sysRoleDOS.stream().map(SysRoleDO::getId).collect(Collectors.toList());
    List<SysRoleMenuDO> sysRoleMenuDOS = sysRoleMenuMapper.queryListByRoleId(roleIds);
    List<String> authorities = sysRoleMenuDOS.stream().map(SysRoleMenuDO::getAuthorities).collect(Collectors.toList());
    Set<String> authoritiesSet = new HashSet<>();
    authorities.forEach(e-> {
      if (StringUtils.isBlank(e)){
        return;
      }
      authoritiesSet.addAll(Set.of(e.split(",")));
    });
    return authoritiesSet;
  }
}