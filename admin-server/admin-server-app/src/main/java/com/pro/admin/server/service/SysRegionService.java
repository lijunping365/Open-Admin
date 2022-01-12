package com.pro.admin.server.service;

import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.dto.create.SysRegionCreateDTO;
import com.pro.admin.server.dto.req.SysRegionReqDTO;
import com.pro.admin.server.dto.resp.SysRegionRespDTO;
import com.pro.admin.server.dto.update.SysRegionUpdateDTO;
import com.pro.admin.server.vo.SysRegionVO;

import java.util.List;

/**
 * 行政区划表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-03 17:14:00
 */
public interface SysRegionService {

  PageResult<SysRegionRespDTO> selectPage(SysRegionReqDTO sysRegionReqDTO);

  SysRegionRespDTO getById(Long id);

  boolean save(SysRegionCreateDTO sysRegionCreateDTO);

  boolean updateById(SysRegionUpdateDTO sysRegionUpdateDTO);

  boolean deleteById(Long id);

  List<SysRegionRespDTO> selectList(Long pid, Integer level);

  List<SysRegionVO> listWithTree(Integer deep);

  List<SysRegionRespDTO> selectListByIds(List<Long> ids);
}

