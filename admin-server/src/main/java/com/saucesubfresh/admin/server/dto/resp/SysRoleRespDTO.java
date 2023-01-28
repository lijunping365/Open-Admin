package com.saucesubfresh.admin.server.dto.resp;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysRoleRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 角色名称
   */
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
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 修改时间
   */
  private LocalDateTime updateTime;
  /**
   * 创建用户
   */
  private Long createUser;
  /**
   * 修改用户
   */
  private Long updateUser;

}
