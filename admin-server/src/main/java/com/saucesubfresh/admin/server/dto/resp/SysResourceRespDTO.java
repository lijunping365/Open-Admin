package com.saucesubfresh.admin.server.dto.resp;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@Data
public class SysResourceRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;

  /**
   * 资源名称
   */
  private String name;

  /**
   * 资源路由
   */
  private String path;

  /**
   * 备注
   */
  private String memo;

  /**
   * 是否是启用选项（1 启用，0 未开启，默认未开启）
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
