package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysUserConvert;
import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysUserDO;
import com.saucesubfresh.admin.server.mapper.SysUserMapper;
import com.saucesubfresh.admin.server.service.SysRoleService;
import com.saucesubfresh.admin.server.service.SysUserService;
import com.saucesubfresh.starter.oauth.domain.UserDetails;
import com.saucesubfresh.starter.oauth.service.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService, UserDetailService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private SysRoleService sysRoleService;

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
    return SysUserConvert.INSTANCE.convert(sysUserDO);
  }

  @Override
  public void saveUser(SysUserCreateDTO sysUserCreateDTO) {
    SysUserDO sysUserDO = SysUserConvert.INSTANCE.convert(sysUserCreateDTO);
    sysUserMapper.saveUser(sysUserDO);
  }

  @Override
  public void update(SysUserUpdateDTO sysUserUpdateDTO) {
    SysUserDO sysUserDO = SysUserConvert.INSTANCE.convert(sysUserUpdateDTO);
    sysUserMapper.updateById(sysUserDO);
  }

  @Override
  public PageResult<SysUserRespDTO> selectPage(SysUserReqDTO sysUserReqDTO) {
    return null;
  }

  @Override
  public void allocRole(SysUserUpdateDTO sysUserUpdateDTO) {
    SysUserDO sysUserDO = SysUserConvert.INSTANCE.convert(sysUserUpdateDTO);
    sysUserMapper.updateById(sysUserDO);
  }

  @Override
  public List<SysMenuRespDTO> getMenus() {
    return null;
  }

  /**
   * 这里需要注意：
   *
   * setAuthorities 设置的是用户拥有的权限，但是如果此时修改了用户的相关权限
   * 想要做到立刻让该用户感知到，需要将该用户的 token 失效掉，让其重新登录就好了
   *
   * @param sysUserDO
   * @return
   */
  private UserDetails convert(SysUserDO sysUserDO){
    List<String> roles = Arrays.asList(StringUtils.split(sysUserDO.getRoles(), ","));
    Set<String> authorities = sysRoleService.getAuthorities(roles);
    UserDetails userDetails = new UserDetails();
    userDetails.setId(sysUserDO.getId());
    userDetails.setUsername(sysUserDO.getUsername());
    userDetails.setPassword(sysUserDO.getPassword());
    userDetails.setMobile(sysUserDO.getMobile());
    userDetails.setAccountLocked(sysUserDO.getAccountStatus() != 1);
    //userDetails.setAuthorities(authorities);
    return userDetails;
  }
}