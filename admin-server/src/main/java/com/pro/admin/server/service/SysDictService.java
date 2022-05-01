package com.pro.admin.server.service;




import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.dto.create.SysDictCreateDTO;
import com.pro.admin.server.dto.req.SysDictReqDTO;
import com.pro.admin.server.dto.resp.SysDictRespDTO;
import com.pro.admin.server.dto.update.SysDictUpdateDTO;

import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-05 14:00:49
 */
public interface SysDictService {

  PageResult<SysDictRespDTO> selectPage(SysDictReqDTO sysDictReqDTO);

  SysDictRespDTO getById(Long id);

  boolean save(SysDictCreateDTO sysDictCreateDTO);

  boolean updateById(SysDictUpdateDTO sysDictUpdateDTO);

  boolean deleteById(Long id);

  List<SysDictRespDTO> selectList(String code);

  List<SysDictRespDTO> selectTree();
}

