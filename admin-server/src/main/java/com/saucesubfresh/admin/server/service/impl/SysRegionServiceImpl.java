package com.saucesubfresh.admin.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.saucesubfresh.admin.common.tree.TreeUtils;
import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.convert.SysRegionConvert;
import com.saucesubfresh.admin.server.dto.create.SysRegionCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysRegionReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysRegionRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysRegionUpdateDTO;
import com.saucesubfresh.admin.server.entity.SysRegionDO;
import com.saucesubfresh.admin.server.mapper.SysRegionMapper;
import com.saucesubfresh.admin.server.service.SysRegionService;
import com.saucesubfresh.admin.server.vo.SysRegionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.saucesubfresh.admin.server.constant.CacheName.REGION_TREE;


@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegionDO> implements SysRegionService {

  @Autowired
  private SysRegionMapper sysRegionMapper;

  @Override
  public PageResult<SysRegionRespDTO> selectPage(SysRegionReqDTO sysRegionReqDTO) {
    return null;
  }

  @Override
  public SysRegionRespDTO getById(Long id) {
    SysRegionDO sysRegionDO = sysRegionMapper.selectById(id);
    return SysRegionConvert.INSTANCE.convert(sysRegionDO);
  }

  @Override
  public boolean save(SysRegionCreateDTO sysRegionCreateDTO) {
    sysRegionMapper.insert(SysRegionConvert.INSTANCE.convert(sysRegionCreateDTO));
    return true;
  }

  @Override
  public boolean updateById(SysRegionUpdateDTO sysRegionUpdateDTO) {
    sysRegionMapper.updateById(SysRegionConvert.INSTANCE.convert(sysRegionUpdateDTO));
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    sysRegionMapper.deleteById(id);
    return true;
  }

  @Override
  public List<SysRegionRespDTO> selectList(Long pid, Integer level) {
    return SysRegionConvert.INSTANCE.convertList(sysRegionMapper.queryList(pid, level));
  }

  @Override
  public List<SysRegionVO> listWithTree(Integer deep) {
    List<SysRegionDO> regionDOS = sysRegionMapper.listWithTree(deep);
    List<SysRegionVO> regionVOList = mapDoToVo(regionDOS);
    regionVOList = new TreeUtils<SysRegionVO>().buildTree(regionVOList);
    return regionVOList;
  }

  @Override
  public List<SysRegionRespDTO> selectListByIds(List<Long> ids) {
    List<SysRegionDO> regionDOS = this.sysRegionMapper.selectBatchIds(ids);
    return SysRegionConvert.INSTANCE.convertList(regionDOS);
  }

  private List<SysRegionVO> mapDoToVo(List<SysRegionDO> regionDOS) {
    return !CollectionUtils.isEmpty(regionDOS) ? regionDOS.stream().map(item -> {
      SysRegionVO regionVO = new SysRegionVO();
      regionVO.setId(item.getId());
      regionVO.setPid(item.getPid());
      regionVO.setSort(item.getSort());
      regionVO.setValue(item.getId());
      regionVO.setLabel(item.getName());
      return regionVO;
    }).collect(Collectors.toList()) : Collections.emptyList();
  }

}