package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.tree.TreeUtils;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysMenuConvert;
import com.saucesubfresh.admin.server.convert.SysUserConvert;
import com.saucesubfresh.admin.server.dto.create.SysUserCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysUserReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysUserRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysUserUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysAccessDO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.entity.SysRoleDO;
import com.saucesubfresh.admin.server.entity.SysUserDO;
import com.saucesubfresh.admin.server.mapper.SysAccessMapper;
import com.saucesubfresh.admin.server.mapper.SysMenuMapper;
import com.saucesubfresh.admin.server.mapper.SysRoleMapper;
import com.saucesubfresh.admin.server.mapper.SysUserMapper;
import com.saucesubfresh.admin.server.service.SysUserService;
import com.saucesubfresh.admin.server.vo.MenuTreeVO;
import com.saucesubfresh.starter.oauth.domain.UserDetails;
import com.saucesubfresh.starter.oauth.service.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService, UserDetailService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private SysRoleMapper sysRoleMapper;

  @Autowired
  private SysMenuMapper sysMenuMapper;

  @Autowired
  private SysAccessMapper sysAccessMapper;

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
    Page<SysUserDO> page = sysUserMapper.queryPage(sysUserReqDTO);
    IPage<SysUserRespDTO> convert = page.convert(SysUserConvert.INSTANCE::convert);
    return PageResult.build(convert);
  }

  @Override
  public void allocRole(SysUserUpdateDTO sysUserUpdateDTO) {
    SysUserDO sysUserDO = SysUserConvert.INSTANCE.convert(sysUserUpdateDTO);
    sysUserMapper.updateById(sysUserDO);
  }

  @Override
  public List<MenuTreeVO> getUserMenus(Long userId) {
    SysUserDO sysUserDO = sysUserMapper.selectById(userId);
    List<SysMenuDO> sysMenuDOS = getMenusList(sysUserDO.getRoles());
    List<MenuTreeVO> menuTree = SysMenuConvert.INSTANCE.convertListVO(sysMenuDOS);
    menuTree = new TreeUtils<MenuTreeVO>().buildTree(menuTree);
    return menuTree;
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
    if(Objects.isNull(sysUserDO)){
      return null;
    }

    UserDetails userDetails = new UserDetails();
    userDetails.setId(sysUserDO.getId());
    userDetails.setUsername(sysUserDO.getUsername());
    userDetails.setPassword(sysUserDO.getPassword());
    userDetails.setMobile(sysUserDO.getMobile());
    userDetails.setAccountLocked(sysUserDO.getAccountStatus() != 1);

    String roles = sysUserDO.getRoles();
    List<String> authoritiesList = getAuthorities(roles);
    userDetails.setAuthorities(authoritiesList);
    return userDetails;
  }

  private List<String> getAuthorities(String roles){
    if (StringUtils.isBlank(roles)){
      return Collections.emptyList();
    }

    List<SysRoleDO> roleDOS = sysRoleMapper.selectBatchIds(Arrays.asList(roles.split(",")));
    Set<String> accessIdSet = new HashSet<>();
    roleDOS.forEach(e->{
      List<String> accessIds = Arrays.asList(StringUtils.split(e.getAccess(), ","));
      accessIdSet.addAll(accessIds);
    });

    if (CollectionUtils.isEmpty(accessIdSet)){
      return Collections.emptyList();
    }

    List<SysAccessDO> accessDOS = sysAccessMapper.selectBatchIds(accessIdSet);
    return accessDOS.stream().map(SysAccessDO::getPath).collect(Collectors.toList());
  }

  private List<SysMenuDO> getMenusList(String roles){
    if (StringUtils.isBlank(roles)){
      return Collections.emptyList();
    }

    List<SysRoleDO> roleDOS = sysRoleMapper.selectBatchIds(Arrays.asList(roles.split(",")));
    Set<String> menuIdSet = new HashSet<>();
    roleDOS.forEach(e->{
      List<String> menuIds = Arrays.asList(StringUtils.split(e.getMenus(), ","));
      menuIdSet.addAll(menuIds);
    });
    return sysMenuMapper.selectBatchIds(menuIdSet);
  }
}