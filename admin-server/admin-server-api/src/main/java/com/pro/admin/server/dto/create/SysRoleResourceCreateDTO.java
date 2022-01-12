package com.pro.admin.server.dto.create;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
@ApiModel("")
@Data
public class SysRoleResourceCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("编号")
  private Long id;
  @ApiModelProperty("角色编号")
  private Long roleId;
  @ApiModelProperty("资源编号")
  private Long resourceId;
  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;
  @ApiModelProperty("更新时间")
  private Long createUser;

}