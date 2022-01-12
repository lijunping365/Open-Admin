package com.pro.admin.server.dto.resp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-12-12 10:52:26
 */
@ApiModel("")
@Data
public class SysTagRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("编号")
  private Long id;
  @ApiModelProperty("标签名称")
  private String name;
  @ApiModelProperty("副标题")
  private String memo;
  @ApiModelProperty("排序")
  private Integer sort;
  @ApiModelProperty("是否启用（0 未启用，1 启用）")
  private Integer enableStatus;
  @ApiModelProperty("删除状态 0删除 1 未删除")
  private Integer deleteStatus;
  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;
  @ApiModelProperty("更新时间")
  private LocalDateTime updateTime;
  @ApiModelProperty("创建人")
  private Long createUser;
  @ApiModelProperty("更新人")
  private Long updateUser;

}
