package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysAccessConvert;
import com.saucesubfresh.admin.server.dto.create.SysAccessCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysAccessReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysAccessRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysAccessUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysAccessDO;
import com.saucesubfresh.admin.server.mapper.SysAccessMapper;
import com.saucesubfresh.admin.server.service.SysAccessService;
import com.saucesubfresh.starter.security.context.UserSecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class SysAccessServiceImpl extends ServiceImpl<SysAccessMapper, SysAccessDO> implements SysAccessService {

  @Autowired
  private SysAccessMapper sysAccessMapper;

  @Override
  public void save(SysAccessCreateDTO sysAccessCreateDTO) {
    SysAccessDO sysAccessDO = SysAccessConvert.INSTANCE.convert(sysAccessCreateDTO);
    sysAccessDO.setCreateTime(LocalDateTime.now());
    sysAccessDO.setCreateUser(UserSecurityContextHolder.getUserId());
    sysAccessMapper.insert(sysAccessDO);
  }

  @Override
  public void update(SysAccessUpdateDTO sysAccessUpdateDTO) {
    SysAccessDO sysAccessDO = SysAccessConvert.INSTANCE.convert(sysAccessUpdateDTO);
    sysAccessDO.setUpdateTime(LocalDateTime.now());
    sysAccessDO.setUpdateUser(UserSecurityContextHolder.getUserId());
    sysAccessMapper.updateById(sysAccessDO);
  }

  @Override
  public void delete(Long id) {
    sysAccessMapper.deleteById(id);
  }

  @Override
  public PageResult<SysAccessRespDTO> selectPage(SysAccessReqDTO sysAccessReqDTO) {
    Page<SysAccessDO> page = sysAccessMapper.queryPage(sysAccessReqDTO);
    IPage<SysAccessRespDTO> convert = page.convert(SysAccessConvert.INSTANCE::convert);
    return PageResult.build(convert);
  }

  @Override
  public List<SysAccessRespDTO> selectAll() {
    List<SysAccessDO> roleDOS = sysAccessMapper.queryList();
    return SysAccessConvert.INSTANCE.convertList(roleDOS);
  }
}