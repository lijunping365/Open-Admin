package com.saucesubfresh.admin.server.dto.update;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysRoleUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 角色名称
   */
  @NotEmpty
  private String name;
  /**
   * 提示
   */
  private String memo;
  /**
   * 排序字段
   */
  private Integer sort;
  /**
   * 菜单id，逗号分隔字符串
   */
  private String menus;
  /**
   * 权限id，逗号分隔字符串
   */
  private String access;

}
