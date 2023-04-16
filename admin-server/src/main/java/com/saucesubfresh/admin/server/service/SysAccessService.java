package com.saucesubfresh.admin.server.service;


import com.saucesubfresh.admin.common.vo.PageResult;
import com.saucesubfresh.admin.server.dto.create.SysAccessCreateDTO;
import com.saucesubfresh.admin.server.dto.req.SysAccessReqDTO;
import com.saucesubfresh.admin.server.dto.resp.SysAccessRespDTO;
import com.saucesubfresh.admin.server.dto.update.SysAccessUpdateDTO;

import java.util.List;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 14:20:48
 */
public interface SysAccessService {

  void save(SysAccessCreateDTO sysAccessCreateDTO);

  void update(SysAccessUpdateDTO sysAccessUpdateDTO);

  void delete(Long id);

  PageResult<SysAccessRespDTO> selectPage(SysAccessReqDTO sysAccessReqDTO);

  List<SysAccessRespDTO> selectAll();
}

