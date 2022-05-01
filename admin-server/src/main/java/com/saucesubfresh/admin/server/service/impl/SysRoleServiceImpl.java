package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysMenuConvert;
import com.saucesubfresh.admin.server.convert.SysResourceConvert;
import com.saucesubfresh.admin.server.convert.SysRoleConvert;
import com.saucesubfresh.admin.server.dto.create.SysRoleCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysRoleReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRoleRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRoleUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.entity.SysResourceDO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import com.saucesubfresh.admin.server.mapper.SysRoleMapper;
import com.saucesubfresh.admin.server.mapper.SysRoleMenuMapper;
import com.saucesubfresh.admin.server.mapper.SysRoleResourceMapper;
import com.saucesubfresh.admin.server.service.SysRoleService;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements SysRoleService {

  @Autowired
  private SysRoleMapper sysRoleMapper;

  @Autowired
  private SysRoleMenuMapper sysRoleMenuMapper;

  @Autowired
  private SysRoleResourceMapper sysRoleResourceMapper;

  @Override
  public boolean save(SysRoleCreateDTO sysRoleCreateDTO) {
    SysRoleDO sysRoleDO = SysRoleConvert.INSTANCE.convert(sysRoleCreateDTO);
    sysRoleDO.setCreateTime(LocalDateTime.now());
    sysRoleDO.setCreateUser(UserSecurityContextHolder.getUserId());
    sysRoleMapper.insert(sysRoleDO);
    return true;
  }

  @Override
  public boolean update(SysRoleUpdateDTO sysRoleUpdateDTO) {
    SysRoleDO sysRoleDO = SysRoleConvert.INSTANCE.convert(sysRoleUpdateDTO);
    sysRoleDO.setUpdateTime(LocalDateTime.now());
    sysRoleDO.setUpdateUser(UserSecurityContextHolder.getUserId());
    sysRoleMapper.updateById(sysRoleDO);
    return true;
  }

  @Override
  public boolean delete(Long id) {
    sysRoleMapper.deleteById(id);
    return true;
  }

  @Override
  public PageResult<SysRoleRespDTO> selectPage(SysRoleReqDTO sysRoleReqDTO) {
    Page<SysRoleDO> page = sysRoleMapper.queryPage(sysRoleReqDTO);
    IPage<SysRoleRespDTO> convert = page.convert(SysRoleConvert.INSTANCE::convert);
    buildRoleDTO(convert);
    return PageResult.build(convert);
  }

  @Override
  public List<SysRoleRespDTO> selectList(Integer total) {
    List<SysRoleDO> roleDOS = sysRoleMapper.queryList(total);
    return SysRoleConvert.INSTANCE.convertList(roleDOS);
  }

  private void buildRoleDTO(IPage<SysRoleRespDTO> convert) {
    if (CollectionUtils.isEmpty(convert.getRecords())) {
      return;
    }
    convert.getRecords().forEach(e -> {
      List<SysMenuDO> menuDOS = sysRoleMenuMapper.loadRoleMenusByRoleId(e.getId());
      e.setMenus(SysMenuConvert.INSTANCE.convertList2(menuDOS));
      List<SysResourceDO> resourceDOS = sysRoleResourceMapper.loadRoleResourcesByRoleId(e.getId());
      e.setResources(SysResourceConvert.INSTANCE.convertList(resourceDOS));
    });
  }
}