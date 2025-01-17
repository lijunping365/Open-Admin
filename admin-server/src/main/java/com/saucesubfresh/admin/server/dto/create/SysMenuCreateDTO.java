package com.saucesubfresh.admin.server.dto.create;


import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysMenuCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 父菜单 id
   */
  private Long pid;
  /**
   * 菜单名称
   */
  private String name;
  /**
   * 菜单路由
   */
  private String route;
  /**
   * 菜单图标
   */
  private String icon;
  /**
   * 菜单排序号
   */
  private Integer sort;
  /**
   * 菜单层级
   */
  private Integer level;
  /**
   * 备注
   */
  private String memo;

}
