package com.pro.admin.server.vo;


import com.pro.admin.common.tree.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李俊平
 * @date 2020-04-20 20:49
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuTreeVO extends TreeNode<MenuTreeVO> {

  private static final long serialVersionUID = 4735649413386632673L;

  /**
   * 名称
   */
  private String name;
  /**
   * 图标
   */
  private String icon;

  /**
   * 菜单路径
   */
  private String path;

}
