package com.pro.admin.server.dto.req;


import com.pro.admin.common.vo.PageQuery;
import lombok.Data;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysRoleReqDTO extends PageQuery {
  private static final long serialVersionUID = 1L;

  private String keyword;
  /**
   * 角色启用状态
   */
  private Integer enableStatus;

}