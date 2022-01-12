package com.pro.admin.server.dto.req;


import com.pro.admin.common.vo.PageQuery;
import lombok.Data;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysMenuReqDTO extends PageQuery {
  private static final long serialVersionUID = -1747584538511637969L;
  /**
   * 菜单名称
   */
  private String name;

}
