package com.pro.admin.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.admin.common.tree.TreeUtils;
import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.convert.SysDictConvert;
import com.pro.admin.server.dto.create.SysDictCreateDTO;
import com.pro.admin.server.dto.req.SysDictReqDTO;
import com.pro.admin.server.dto.resp.SysDictRespDTO;
import com.pro.admin.server.dto.update.SysDictUpdateDTO;
import com.pro.admin.server.entity.SysDictDO;
import com.pro.admin.server.mapper.SysDictMapper;
import com.pro.admin.server.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.pro.admin.server.constant.CacheName.DICT_TREE;


@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictDO> implements SysDictService {

  @Autowired
  private SysDictMapper sysDictMapper;

  @Override
  public List<SysDictRespDTO> selectList(String code) {
    SysDictDO sysDictDO = sysDictMapper.selectOne(Wrappers.<SysDictDO>lambdaQuery().eq(SysDictDO::getCode, code));;
    if (Objects.isNull(sysDictDO)){
      return Collections.emptyList();
    }
    List<SysDictDO> sysDictDOS = sysDictMapper.queryList(sysDictDO.getPid());
    return SysDictConvert.INSTANCE.convertList(sysDictDOS);
  }

  @Cacheable(value = DICT_TREE, key = "#root.methodName")
  @Override
  public List<SysDictRespDTO> selectTree() {
    List<SysDictDO> dictDOS = sysDictMapper.queryAll();
    List<SysDictRespDTO> sysDictDOS = SysDictConvert.INSTANCE.convertList(dictDOS);
    sysDictDOS = new TreeUtils<SysDictRespDTO>().buildTree(sysDictDOS);
    return sysDictDOS;
  }

  @Override
  public PageResult<SysDictRespDTO> selectPage(SysDictReqDTO sysDictReqDTO) {
    Page<SysDictDO> page = sysDictMapper.queryPage(sysDictReqDTO);
    IPage<SysDictRespDTO> convert = page.convert(SysDictConvert.INSTANCE::convert);
    return PageResult.build(convert);
  }

  @Override
  public SysDictRespDTO getById(Long id) {
    SysDictDO sysDictDO = sysDictMapper.selectById(id);
    return SysDictConvert.INSTANCE.convert(sysDictDO);
  }

  @CacheEvict(value = DICT_TREE, allEntries = true)
  @Transactional
  @Override
  public boolean save(SysDictCreateDTO sysDictCreateDTO) {
    sysDictMapper.insert(SysDictConvert.INSTANCE.convert(sysDictCreateDTO));
    return true;
  }

  @CacheEvict(value = DICT_TREE, allEntries = true)
  @Transactional
  @Override
  public boolean updateById(SysDictUpdateDTO sysDictUpdateDTO) {
    sysDictMapper.updateById(SysDictConvert.INSTANCE.convert(sysDictUpdateDTO));
    return true;
  }

  @CacheEvict(value = DICT_TREE, allEntries = true)
  @Transactional
  @Override
  public boolean deleteById(Long id) {
    sysDictMapper.deleteById(id);
    return true;
  }

}