package com.pro.admin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pro.admin.server.dto.req.SysDictReqDTO;
import com.pro.admin.server.entity.SysDictDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-05 14:00:49
 */
@Repository
public interface SysDictMapper extends BaseMapper<SysDictDO> {

  default List<SysDictDO> queryList(Long pid){
    return selectList(Wrappers.<SysDictDO>lambdaQuery().eq(SysDictDO::getPid, pid));
  }

  default List<SysDictDO> queryAll(){
    return selectList(Wrappers.<SysDictDO>lambdaQuery().orderByAsc(SysDictDO::getSort));
  }

  default Page<SysDictDO> queryPage(SysDictReqDTO sysDictReqDTO){
    return selectPage(sysDictReqDTO.page(), Wrappers.<SysDictDO>lambdaQuery()
        .like(StringUtils.isNotBlank(sysDictReqDTO.getName()), SysDictDO::getName, sysDictReqDTO.getName()));
  }
}
