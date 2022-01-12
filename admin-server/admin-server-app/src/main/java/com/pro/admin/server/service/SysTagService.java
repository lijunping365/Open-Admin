package com.pro.admin.server.service;



import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.dto.create.SysTagCreateDTO;
import com.pro.admin.server.dto.del.BatchDelDTO;
import com.pro.admin.server.dto.req.SysTagReqDTO;
import com.pro.admin.server.dto.resp.SysTagRespDTO;
import com.pro.admin.server.dto.update.SysTagUpdateDTO;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-12-12 10:52:26
 */
public interface SysTagService {

  PageResult<SysTagRespDTO> selectPage(SysTagReqDTO sysTagReqDTO);

  SysTagRespDTO getById(Long id);

  boolean save(SysTagCreateDTO sysTagCreateDTO);

  boolean updateById(SysTagUpdateDTO sysTagUpdateDTO);

  boolean deleteBatch(BatchDelDTO batchDTO);

  List<SysTagRespDTO> selectList(Integer tagType);

  List<SysTagRespDTO> selectListByIds(List<Long> tagIds);
}

