package com.saucesubfresh.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.saucesubfresh.admin.server.entity.SysRegionDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 行政区划表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-03 17:14:00
 */
@Repository
public interface SysRegionMapper extends BaseMapper<SysRegionDO> {

  default List<SysRegionDO> queryList(Long pid, Integer level) {
    return selectList(Wrappers.<SysRegionDO>lambdaQuery().eq(SysRegionDO::getPid, pid).eq(SysRegionDO::getLevel, level));
  }

  default List<SysRegionDO> listWithTree(Integer deep) {
    return selectList(Wrappers.<SysRegionDO>lambdaQuery().le(SysRegionDO::getLevel, deep));
  }
}
