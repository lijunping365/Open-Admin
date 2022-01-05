package com.pro.admin.server.entity.authority;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pro.admin.common.enums.CommonStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Data
@TableName("fresh_sys_menu")
public class SysMenuDO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @TableId
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
   *菜单路由
   */
  private String path;
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
  /**
   * 菜单状态启用状态（1 启用，0 禁用）
   */
  private CommonStatusEnum enableStatus;

}