package com.pro.admin.server.dto.create;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("菜单表")
@Data
public class SysResourceCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("主键id")
  private Long id;
  @ApiModelProperty("资源名称")
  private String name;
  @ApiModelProperty("资源路由")
  private String path;
  @ApiModelProperty("备注")
  private String memo;
  @ApiModelProperty("是否是启用选项（1 启用，0 未开启，默认未开启）")
  private Integer enableStatus;
  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;
  @ApiModelProperty("修改时间")
  private LocalDateTime updateTime;
  @ApiModelProperty("创建人")
  private Long createUser;
  @ApiModelProperty("修改人")
  private Long updateUser;

}
