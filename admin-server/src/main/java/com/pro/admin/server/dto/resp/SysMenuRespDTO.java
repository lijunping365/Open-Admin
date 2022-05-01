package com.pro.admin.server.dto.resp;


import com.pro.admin.common.tree.TreeNode;
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
   *
   */
  private String path;
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
  /**
   * 菜单状态
   */
  private Integer enableStatus;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 修改时间
   */
  private LocalDateTime updateTime;
  /**
   * 创建人
   */
  private Long createUser;
  /**
   * 修改人
   */
  private Long updateUser;

}
