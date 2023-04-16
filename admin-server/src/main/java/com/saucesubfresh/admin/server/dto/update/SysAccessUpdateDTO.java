package com.saucesubfresh.admin.server.dto.update;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysAccessUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 权限名称
   */
  @NotBlank(message = "权限名称不能为空")
  private String name;
  /**
   * 请求路径
   */
  @NotBlank(message = "权限路径不能为空")
  private String path;

}
