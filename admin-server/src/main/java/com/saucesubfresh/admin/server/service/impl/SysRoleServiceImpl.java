package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysRoleConvert;
import com.saucesubfresh.admin.server.dto.create.SysRoleCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysRoleReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import com.saucesubfresh.admin.server.mapper.SysRoleMapper;
import com.saucesubfresh.admin.server.service.SysRoleService;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements SysRoleService {

  @Autowired
  private SysRoleMapper sysRoleMapper;

  @Override
  public void save(SysRoleCreateDTO sysRoleCreateDTO) {
    SysRoleDO sysRoleDO = SysRoleConvert.INSTANCE.convert(sysRoleCreateDTO);
    sysRoleDO.setCreateTime(LocalDateTime.now());
    sysRoleDO.setCreateUser(UserSecurityContextHolder.getUserId());
    sysRoleMapper.insert(sysRoleDO);
  }

  @Override
  public void update(SysRoleUpdateDTO sysRoleUpdateDTO) {
    SysRoleDO sysRoleDO = SysRoleConvert.INSTANCE.convert(sysRoleUpdateDTO);
    sysRoleDO.setUpdateTime(LocalDateTime.now());
    sysRoleDO.setUpdateUser(UserSecurityContextHolder.getUserId());
    sysRoleMapper.updateById(sysRoleDO);
  }

  @Override
  public void delete(Long id) {
    sysRoleMapper.deleteById(id);
  }

  @Override
  public PageResult<SysRoleRespDTO> selectPage(SysRoleReqDTO sysRoleReqDTO) {
    Page<SysRoleDO> page = sysRoleMapper.queryPage(sysRoleReqDTO);
    IPage<SysRoleRespDTO> convert = page.convert(SysRoleConvert.INSTANCE::convert);
    return PageResult.build(convert);
  }

  @Override
  public List<SysRoleRespDTO> selectAll() {
    List<SysRoleDO> roleDOS = sysRoleMapper.queryList();
    return SysRoleConvert.INSTANCE.convertList(roleDOS);
  }

  @Override
  public Set<String> getAuthorities(List<String> roles) {
    return null;
  }


  @Override
  public void allocMenu(SysRoleUpdateDTO sysRoleUpdateDTO) {
    SysRoleDO sysRoleDO = SysRoleConvert.INSTANCE.convert(sysRoleUpdateDTO);
    sysRoleMapper.updateById(sysRoleDO);
  }
}