package com.pro.admin.server.dto.req;


import com.pro.admin.common.vo.DateTimePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-12-12 10:52:26
 */
@ApiModel("")
@Data
public class SysTagReqDTO extends DateTimePageQuery implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("分组id")
  private Long groupId;
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

}
