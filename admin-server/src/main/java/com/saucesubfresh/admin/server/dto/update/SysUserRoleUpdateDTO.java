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
public class SysUserRoleUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull
  private Long userId;

  @NotEmpty
  private List<Long> roleId;

}
