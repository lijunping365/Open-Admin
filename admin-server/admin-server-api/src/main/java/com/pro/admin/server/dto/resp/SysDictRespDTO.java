package com.pro.admin.server.dto.resp;


import com.pro.admin.common.tree.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-05 14:00:49
 */
@ApiModel("")
@Data
public class SysDictRespDTO extends TreeNode<SysDictRespDTO> implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("主键")
  private Long id;
  @ApiModelProperty("父字典类型主键")
  private Long pid;
  @ApiModelProperty("字典名")
  private String name;
  @ApiModelProperty("字典唯一标识")
  private String code;
  @ApiModelProperty("排序")
  private Integer sort;
  @ApiModelProperty("层级")
  private Integer level;
  @ApiModelProperty("是否是启用选项")
  private Integer enableStatus;
  @ApiModelProperty("备注")
  private String memo;
  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;
  @ApiModelProperty("更新时间")
  private LocalDateTime updateTime;
  @ApiModelProperty("创建人")
  private Long createUser;
  @ApiModelProperty("更新人")
  private Long updateUser;

}
