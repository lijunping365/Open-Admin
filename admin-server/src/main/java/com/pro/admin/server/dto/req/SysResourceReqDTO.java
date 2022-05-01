package com.pro.admin.server.dto.req;


import com.pro.admin.common.vo.DateTimePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@ApiModel("菜单表")
@Data
public class SysResourceReqDTO extends DateTimePageQuery implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("资源名称")
  private String name;
  

}
