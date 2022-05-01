package com.pro.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.admin.server.convert.SysRoleConvert;
import com.pro.admin.server.dto.resp.SysRoleRespDTO;
import com.pro.admin.server.dto.update.SysUserRoleUpdateDTO;
import com.pro.admin.server.entity.SysRoleDO;
import com.pro.admin.server.entity.SysUserRoleDO;
import com.pro.admin.server.mapper.SysUserRoleMapper;
import com.pro.admin.server.service.SysUserRoleService;
import com.pro.starter.security.annotation.PreAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleDO> implements SysUserRoleService {

  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;

  @Override
  public List<SysRoleRespDTO> listRoles(Long userId) {
    List<SysRoleDO> sysRoleDOList = sysUserRoleMapper.loadUserRolesByUserId(userId);
    return SysRoleConvert.INSTANCE.convertList(sysRoleDOList);
  }

  @Override
  @PreAuthorization
  @Transactional
  public boolean allocRole(SysUserRoleUpdateDTO sysUserRoleUpdateDTO) {
    //先删除原有关系
    List<SysRoleRespDTO> sysRoleRespDTOS = this.listRoles(sysUserRoleUpdateDTO.getUserId());
    if (!sysRoleRespDTOS.isEmpty()) {
      for (SysRoleRespDTO sysRoleRespDTO : sysRoleRespDTOS) {
        sysUserRoleMapper.deleteUserRoles(sysUserRoleUpdateDTO.getUserId(), sysRoleRespDTO.getId());
      }
    }
    //插入新关系
    for (Long roleId : sysUserRoleUpdateDTO.getRoleId()) {
      SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
      sysUserRoleDO.setUserId(sysUserRoleUpdateDTO.getUserId());
      sysUserRoleDO.setRoleId(roleId);
      sysUserRoleMapper.insert(sysUserRoleDO);
    }
    return true;
  }
}