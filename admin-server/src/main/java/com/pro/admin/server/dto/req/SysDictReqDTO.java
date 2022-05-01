package com.pro.admin.server.dto.req;


import com.pro.admin.common.vo.DateTimePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-05 14:00:49
 */
@ApiModel("")
@Data
public class SysDictReqDTO extends DateTimePageQuery implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("字典名")
  private String name;
  @ApiModelProperty("字典唯一标识")
  private String code;
  @ApiModelProperty("是否是启用选项")
  private Integer enableStatus;

}
