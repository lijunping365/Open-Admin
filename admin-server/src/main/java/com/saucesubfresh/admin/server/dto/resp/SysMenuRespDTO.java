package com.saucesubfresh.admin.server.dto.resp;


import com.saucesubfresh.admin.common.tree.TreeNode;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysMenuRespDTO extends TreeNode<SysMenuRespDTO> implements Serializable {
  private static final long serialVersionUID = 1L;

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
   * 菜单层级
   */
  private Integer level;
  /**
   * 备注
   */
  private String memo;

}
