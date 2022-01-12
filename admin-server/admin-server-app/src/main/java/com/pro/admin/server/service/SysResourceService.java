package com.pro.admin.server.service;




import com.pro.admin.common.vo.PageResult;
import com.pro.admin.server.dto.create.SysResourceCreateDTO;
import com.pro.admin.server.dto.req.SysResourceReqDTO;
import com.pro.admin.server.dto.resp.SysResourceRespDTO;
import com.pro.admin.server.dto.update.SysResourceUpdateDTO;

import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
public interface SysResourceService {

  PageResult<SysResourceRespDTO> selectPage(SysResourceReqDTO sysResourceReqDTO);

  SysResourceRespDTO getById(Long id);

  boolean save(SysResourceCreateDTO sysResourceCreateDTO);

  boolean updateById(SysResourceUpdateDTO sysResourceUpdateDTO);

  boolean deleteById(Long id);

  List<SysResourceRespDTO> selectList(Integer total);
}
