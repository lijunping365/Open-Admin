package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysUserConvert;
import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import com.saucesubfresh.admin.server.entity.SysUserDO;
import com.saucesubfresh.admin.server.mapper.SysUserMapper;
import com.saucesubfresh.admin.server.mapper.SysUserRoleMapper;
import com.saucesubfresh.admin.server.service.SysUserService;
import com.saucesubfresh.starter.oauth.domain.UserDetails;
import com.saucesubfresh.starter.oauth.service.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService, UserDetailService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;

  @Override
  public UserDetails loadUserByUsername(String username) {
    SysUserDO sysUserDO = sysUserMapper.loadUserByUsername(username);
    return convert(sysUserDO);
  }

  @Override
  public UserDetails loadUserByMobile(String mobile) {
    SysUserDO sysUserDO = sysUserMapper.loadUserByMobile(mobile);
    return convert(sysUserDO);
  }

  @Override
  public SysUserRespDTO loadUserByUserId(Long userId) {
    SysUserDO sysUserDO = sysUserMapper.loadUserByUserId(userId);
    SysUserRespDTO sysUserRespDTO = SysUserConvert.INSTANCE.convert(sysUserDO);
    sysUserRespDTO.setAuthorities(getUserRoles(sysUserDO.getId()));
    return sysUserRespDTO;
  }

  @Override
  public SysUserRespDTO saveUser(SysUserCreateDTO sysUserCreateDTO) {
    SysUserDO sysUserDO = SysUserConvert.INSTANCE.convert(sysUserCreateDTO);
    sysUserMapper.saveUser(sysUserDO);
    SysUserRespDTO sysUserRespDTO = SysUserConvert.INSTANCE.convert(sysUserDO);
    sysUserRespDTO.setAuthorities(getUserRoles(sysUserDO.getId()));
    return sysUserRespDTO;
  }

  @Override
  public boolean updateBatch(SysUserUpdateDTO sysUserUpdateDTO) {
    List<SysUserDO> sysUserDOS = sysUserUpdateDTO.getUserIds().stream().map(e -> sysUserMapper.loadUserByUserId(e)).collect(Collectors.toList());
    this.updateBatchById(sysUserDOS);
    return true;
  }

  @Override
  public PageResult<SysUserRespDTO> selectPage(SysUserReqDTO sysUserReqDTO) {
    return null;
  }

  private List<String> getUserRoles(Long userId) {
    List<SysRoleDO> sysRoleDOList = sysUserRoleMapper.loadUserRolesByUserId(userId);
    if (CollectionUtils.isEmpty(sysRoleDOList)) {
      return Collections.emptyList();
    }
    return sysRoleDOList.stream().filter(e -> StringUtils.isNotBlank(e.getName())).map(SysRoleDO::getName).collect(Collectors.toList());
  }

  private UserDetails convert(SysUserDO sysUserDO){
    UserDetails userDetails = new UserDetails();
    userDetails.setId(sysUserDO.getId());
    userDetails.setUsername(sysUserDO.getUsername());
    userDetails.setPassword(sysUserDO.getPassword());
    userDetails.setMobile(sysUserDO.getMobile());
    userDetails.setAccountLocked(sysUserDO.getAccountStatus() != 1);
    userDetails.setAuthorities(getUserRoles(sysUserDO.getId()));
    return userDetails;
  }
}