package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.saucesubfresh.admin.server.convert.SysRoleResourceConvert;
import com.saucesubfresh.admin.server.dto.resp.SysResourceRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleResourceUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysRoleResourceDO;
import com.saucesubfresh.admin.server.mapper.SysRoleResourceMapper;
import com.saucesubfresh.admin.server.service.SysRoleResourceService;
import com.saucesubfresh.starter.security.annotation.PreAuthorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.saucesubfresh.admin.server.constant.CacheName.ROLE_RESOURCE_MAP;

@Slf4j
@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResourceDO> implements SysRoleResourceService {

  @Autowired
  private SysRoleResourceMapper sysRoleResourceMapper;

  public Map<String, List<String>> loadResourceRolesMap() {
    try {
      List<Map<String, String>> roleMenuList = sysRoleResourceMapper.loadResourceRolesMap();
      return mapResourceRole(roleMenuList);
    } catch (Exception e){
      return Collections.emptyMap();
    }
  }

  @Override
  public List<SysResourceRespDTO> listResource(Long roleId) {
    List<SysRoleResourceDO> resourceDOS = sysRoleResourceMapper.queryList(roleId);
    return SysRoleResourceConvert.INSTANCE.convertList(resourceDOS);
  }

  @Override
  public boolean allocResource(SysRoleResourceUpdateDTO sysRoleResourceUpdateDTO) {
    //先删除原有关系
    List<SysResourceRespDTO> sysResourceRespDTOS = this.listResource(sysRoleResourceUpdateDTO.getRoleId());
    if (!sysResourceRespDTOS.isEmpty()) {
      for (SysResourceRespDTO sysResourceRespDTO : sysResourceRespDTOS) {
        sysRoleResourceMapper.deleteRoleResource(sysRoleResourceUpdateDTO.getRoleId(), sysResourceRespDTO.getId());
      }
    }
    //插入新关系
    for (Long resourceId : sysRoleResourceUpdateDTO.getResourceId()) {
      SysRoleResourceDO sysRoleResourceDO = new SysRoleResourceDO();
      sysRoleResourceDO.setRoleId(sysRoleResourceUpdateDTO.getRoleId());
      sysRoleResourceDO.setResourceId(resourceId);
      sysRoleResourceMapper.insert(sysRoleResourceDO);
    }
    return true;
  }

  private Map<String, List<String>> mapResourceRole(List<Map<String, String>> roleResourceList) {
    Map<String, List<String>> resourceRoleMap = new HashMap<>();
    for (Map<String, String> map : roleResourceList) {
      String role = map.get("role");
      String url = map.get("url");
      if (resourceRoleMap.containsKey(url)) {
        List<String> roles = resourceRoleMap.get(url);
        roles.add(role);
      } else {
        resourceRoleMap.put(url, new ArrayList<>(Collections.singletonList(role)));
      }
    }
    return resourceRoleMap;
  }
}