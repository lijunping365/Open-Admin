package com.saucesubfresh.admin.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Data
@TableName("open_admin_role")
public class SysRoleDO extends BaseDO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @TableId
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
   * 菜单状态启用状态（1 启用，0 禁用）
   */
  private CommonStatusEnum enableStatus;

}