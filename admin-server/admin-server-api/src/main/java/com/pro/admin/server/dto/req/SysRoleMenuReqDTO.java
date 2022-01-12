package com.pro.admin.server.dto.req;


import com.pro.admin.common.vo.PageQuery;
import lombok.Data;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysRoleMenuReqDTO extends PageQuery {
  private static final long serialVersionUID = 1L;
  /**
   *
   */
  private Long roleId;
  /**
   *
   */
  private Long menuId;

}
