package com.saucesubfresh.admin.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Data
@TableName("open_admin_role_menu")
public class SysRoleMenuDO extends BaseDO implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 角色 id
   */
  private Long roleId;
  /**
   * 菜单 id
   */
  private Long menuId;

  /**
   * 权限，逗号分隔字符串
   */
  private String authorities;
}
