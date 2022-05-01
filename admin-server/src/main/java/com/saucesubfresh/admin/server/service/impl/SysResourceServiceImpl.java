package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysResourceConvert;
import com.saucesubfresh.admin.server.dto.create.SysResourceCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysResourceReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysResourceRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysResourceUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysResourceDO;
import com.saucesubfresh.admin.server.mapper.SysResourceMapper;
import com.saucesubfresh.admin.server.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResourceDO> implements SysResourceService {

  @Autowired
  private SysResourceMapper sysResourceMapper;

  @Override
  public List<SysResourceRespDTO> selectList(Integer total) {
    List<SysResourceDO> sysResourceDOS = sysResourceMapper.queryList(total);
    return SysResourceConvert.INSTANCE.convertList2(sysResourceDOS);
  }

  @Override
  public PageResult<SysResourceRespDTO> selectPage(SysResourceReqDTO sysResourceReqDTO) {
    Page<SysResourceDO> page = sysResourceMapper.queryPage(sysResourceReqDTO);
    IPage<SysResourceRespDTO> convert = page.convert(SysResourceConvert.INSTANCE::convert);
    return PageResult.build(convert);
  }

  @Override
  public SysResourceRespDTO getById(Long id) {
    SysResourceDO sysResourceDO = sysResourceMapper.selectById(id);
    return SysResourceConvert.INSTANCE.convert(sysResourceDO);
  }

  @Override
  public boolean save(SysResourceCreateDTO sysResourceCreateDTO) {
    sysResourceMapper.insert(SysResourceConvert.INSTANCE.convert(sysResourceCreateDTO));
    return true;
  }

  @Override
  public boolean updateById(SysResourceUpdateDTO sysResourceUpdateDTO) {
    sysResourceMapper.updateById(SysResourceConvert.INSTANCE.convert(sysResourceUpdateDTO));
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    sysResourceMapper.deleteById(id);
    return true;
  }

}