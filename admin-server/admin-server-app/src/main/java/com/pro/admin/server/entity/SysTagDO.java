package com.pro.admin.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pro.admin.common.enums.CommonStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-12-12 10:52:26
 */
@Data
@TableName("fresh_sys_tag")
public class SysTagDO extends BaseDO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 编号
   */
  @TableId
  private Long id;
  /**
   * 分组id
   */
  private Long groupId;
  /**
   * 标签名称
   */
  private String name;
  /**
   * 副标题
   */
  private String memo;
  /**
   * 排序
   */
  private Integer sort;
  /**
   * 是否启用（0 未启用，1 启用）
   */
  private CommonStatusEnum enableStatus;
  /**
   * 删除状态 0删除 1 未删除
   */
  private CommonStatusEnum deleteStatus;
}
