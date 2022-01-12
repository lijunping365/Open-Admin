package com.pro.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.admin.common.enums.CommonStatusEnum;
import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.convert.SysTagConvert;
import com.pro.admin.server.dto.create.SysTagCreateDTO;
import com.pro.admin.server.dto.del.BatchDelDTO;
import com.pro.admin.server.dto.req.SysTagReqDTO;
import com.pro.admin.server.dto.resp.SysTagRespDTO;
import com.pro.admin.server.dto.update.SysTagUpdateDTO;
import com.pro.admin.server.entity.SysTagDO;
import com.pro.admin.server.mapper.SysTagMapper;
import com.pro.admin.server.service.SysTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SysTagServiceImpl extends ServiceImpl<SysTagMapper, SysTagDO> implements SysTagService {

  @Autowired
  private SysTagMapper sysTagMapper;

  @Override
  public PageResult<SysTagRespDTO> selectPage(SysTagReqDTO sysTagReqDTO) {
    Page<SysTagDO> page = sysTagMapper.queryPage(sysTagReqDTO);
    IPage<SysTagRespDTO> convert = page.convert(SysTagConvert.INSTANCE::convert);
    return PageResult.build(convert);
  }

  @Override
  public SysTagRespDTO getById(Long id) {
    SysTagDO sysTagDO = sysTagMapper.selectById(id);
    return SysTagConvert.INSTANCE.convert(sysTagDO);
  }

  @Transactional
  @Override
  public boolean save(SysTagCreateDTO sysTagCreateDTO) {
    sysTagMapper.insert(SysTagConvert.INSTANCE.convert(sysTagCreateDTO));
    return true;
  }

  @Transactional
  @Override
  public boolean updateById(SysTagUpdateDTO sysTagUpdateDTO) {
    sysTagMapper.updateById(SysTagConvert.INSTANCE.convert(sysTagUpdateDTO));
    return true;
  }

  @Transactional
  @Override
  public boolean deleteBatch(BatchDelDTO batchDTO) {
    List<SysTagDO> collect = batchDTO.getIds().stream().map(e -> {
      SysTagDO sysTagDO = sysTagMapper.selectById(e);
      sysTagDO.setDeleteStatus(CommonStatusEnum.YES);
      return sysTagDO;
    }).collect(Collectors.toList());
    this.updateBatchById(collect);
    return true;
  }

  @Override
  public List<SysTagRespDTO> selectList(Integer tagType) {
    List<SysTagDO> sysTagDOS = sysTagMapper.queryList(tagType);
    return SysTagConvert.INSTANCE.convertList(sysTagDOS);
  }

  @Override
  public List<SysTagRespDTO> selectListByIds(List<Long> tagIds) {
    List<SysTagDO> sysTagDOS = this.sysTagMapper.selectBatchIds(tagIds);
    return SysTagConvert.INSTANCE.convertList(sysTagDOS);
  }


}