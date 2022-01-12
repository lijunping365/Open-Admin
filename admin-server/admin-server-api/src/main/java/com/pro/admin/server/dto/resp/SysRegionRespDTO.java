package com.pro.admin.server.dto.resp;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行政区划表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-03 17:14:00
 */
@ApiModel("行政区划表")
@Data
public class SysRegionRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("主键id")
  private Long id;
  @ApiModelProperty("父类id")
  private Long pid;
  @ApiModelProperty("code")
  private String code;
  @ApiModelProperty("名称")
  private String name;
  @ApiModelProperty("简称")
  private String shortName;
  @ApiModelProperty("排序")
  private Integer sort;
  @ApiModelProperty("级别 1:省级 2:市级 3:区/县级 4:街道/镇级")
  private Integer level;
  @ApiModelProperty("经度")
  private String lat;
  @ApiModelProperty("维度")
  private String lng;
  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;
  @ApiModelProperty("修改时间")
  private LocalDateTime updateTime;
  @ApiModelProperty("创建人")
  private Long createUser;
  @ApiModelProperty("修改人")
  private Long updateUser;
  @ApiModelProperty("是否展示")
  private Integer showStatus;
  @ApiModelProperty("简称-全拼")
  private String fullPinyin;
  @ApiModelProperty("简称-简拼")
  private String simplePinyin;
  @ApiModelProperty("省级地区")
  private String area;
  @ApiModelProperty("是否是省会")
  private Integer isPc;

}
