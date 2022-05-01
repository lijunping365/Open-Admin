package com.saucesubfresh.admin.server.dto.create;


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
@Data
public class SysRegionCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;

  /**
   * 父类id
   */
  private Long pid;

  /**
   * code
   */
  private String code;

  /**
   * 名称
   */
  private String name;

  /**
   * 简称
   */
  private String shortName;

  /**
   * 排序
   */
  private Integer sort;

  /**
   * 级别 1:省级 2:市级 3:区/县级 4:街道/镇级
   */
  private Integer level;

  /**
   * 经度
   */
  private String lat;

  /**
   * 纬度
   */
  private String lng;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 创建时间
   */
  private LocalDateTime updateTime;

  /**
   * 创建人
   */
  private Long createUser;

  /**
   * 修改人
   */
  private Long updateUser;

  /**
   * 是否展示
   */
  private Integer showStatus;

  /**
   * 简称-全拼
   */
  private String fullPinyin;

  /**
   * 简称-简拼
   */
  private String simplePinyin;

  /**
   * 省级地区
   */
  private String area;

  /**
   * 是否是省会
   */
  private Integer isPc;

}
