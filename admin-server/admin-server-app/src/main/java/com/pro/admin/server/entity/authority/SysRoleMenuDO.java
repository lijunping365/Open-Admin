package com.pro.admin.server.entity.authority;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Data
@TableName("fresh_sys_role_menu")
public class SysRoleMenuDO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   *
   */
  @TableId
  private Long id;
  /**
   *
   */
  private Long roleId;
  /**
   *
   */
  private Long menuId;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 创建人
   */
  private Long createUser;


}
