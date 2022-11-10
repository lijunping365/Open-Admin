package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.tree.TreeUtils;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysMenuConvert;
import com.saucesubfresh.admin.server.dto.create.SysMenuCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysMenuReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysMenuRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysMenuUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysMenuDO;
import com.saucesubfresh.admin.server.mapper.SysMenuMapper;
import com.saucesubfresh.admin.server.service.SysMenuService;
import com.saucesubfresh.admin.server.vo.MenuTreeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuDO> implements SysMenuService {

  @Autowired
  private SysMenuMapper sysMenuMapper;

  @Override
  public List<MenuTreeVO> getMenuTree() {
    List<SysMenuDO> menuList = sysMenuMapper.selectList(Wrappers.lambdaQuery());
    List<MenuTreeVO> menuTree = SysMenuConvert.INSTANCE.convertListVO(menuList);
    menuTree = new TreeUtils<MenuTreeVO>().buildTree(menuTree);
    return menuTree;
  }

  @Override
  public PageResult<SysMenuRespDTO> selectPage(SysMenuReqDTO sysMenuReqDTO) {
    List<SysMenuDO> menuList = sysMenuMapper.queryList(sysMenuReqDTO);
    List<SysMenuRespDTO> menuRespDTOS = SysMenuConvert.INSTANCE.convertList(menuList);
    long count = menuRespDTOS.stream().filter(e -> Objects.equals(e.getLevel(), 1)).count();
    menuRespDTOS = new TreeUtils<SysMenuRespDTO>().buildTree(menuRespDTOS);
    return PageResult.build(menuRespDTOS, count, sysMenuReqDTO.getCurrent(), sysMenuReqDTO.getPageSize());
  }

  @Override
  public SysMenuRespDTO getItem(Long id) {
    SysMenuDO sysMenuDO = sysMenuMapper.selectById(id);
    return SysMenuConvert.INSTANCE.convert(sysMenuDO);
  }

  @Override
  public void save(SysMenuCreateDTO sysMenuCreateDTO) {
    if (sysMenuCreateDTO.getPid() != null && !sysMenuCreateDTO.getPid().equals(0L)) {
      SysMenuDO sysMenuDO = sysMenuMapper.selectById(sysMenuCreateDTO.getPid());
      sysMenuCreateDTO.setLevel(sysMenuDO.getLevel() + 1);
    }
    sysMenuMapper.insert(SysMenuConvert.INSTANCE.convert(sysMenuCreateDTO));
  }

  @Override
  public void update(SysMenuUpdateDTO sysMenuUpdateDTO) {
    if (sysMenuUpdateDTO.getPid() != null && !sysMenuUpdateDTO.getPid().equals(0L)) {
      SysMenuDO sysMenuDO = sysMenuMapper.selectById(sysMenuUpdateDTO.getPid());
      sysMenuUpdateDTO.setLevel(sysMenuDO.getLevel() + 1);
    }
    sysMenuMapper.updateById(SysMenuConvert.INSTANCE.convert(sysMenuUpdateDTO));
  }

  @Override
  public void delete(Long id) {
    sysMenuMapper.deleteById(id);
  }
}