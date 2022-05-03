package com.saucesubfresh.admin.server.dto.update;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysRoleMenuUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull
  private Long roleId;

  @NotEmpty
  private List<Long> menuId;

  private String authorities;

}
