package com.pro.admin.server.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 资源表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysResourceVO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 资源名称
   */
  private String name;

}
