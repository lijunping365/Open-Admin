package com.saucesubfresh.admin.server.dto.update;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
@Data
public class SysRoleResourceUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 角色编号
   */
  @NotNull
  private Long roleId;

  /**
   * 资源编号
   */
  @NotEmpty
  private List<Long> resourceId;

}
