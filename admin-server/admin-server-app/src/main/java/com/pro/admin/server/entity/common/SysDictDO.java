package com.pro.admin.server.entity.common;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pro.admin.common.enums.CommonStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-05 14:00:49
 */
@Data
@TableName("fresh_sys_dict")
public class SysDictDO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId
  private Long id;
  /**
   * 父字典类型主键
   */
  private Long pid;
  /**
   * 字典名
   */
  private String name;
  /**
   * 字典唯一标识
   */
  private String code;
  /**
   * 排序
   */
  private Integer sort;
  /**
   * 层级
   */
  private Integer level;
  /**
   * 是否是启用选项
   */
  private CommonStatusEnum enableStatus;
  /**
   * 备注
   */
  private String memo;

}
