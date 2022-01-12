package com.pro.admin.server.dto.update;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("")
@Data
public class SysRoleResourceUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull
  @ApiModelProperty("角色编号")
  private Long roleId;

  @NotEmpty
  @ApiModelProperty("资源编号")
  private List<Long> resourceId;

}
