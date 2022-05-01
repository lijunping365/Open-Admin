package com.saucesubfresh.admin.server.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysMenuVO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 菜单名称
   */
  private String name;
  /**
   * 子菜单
   */
  private List<SysMenuVO> children;

}
