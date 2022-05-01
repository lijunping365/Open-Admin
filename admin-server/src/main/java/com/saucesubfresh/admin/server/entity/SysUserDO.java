package com.saucesubfresh.admin.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-08-23 09:27:19
 */
@Data
@TableName("fresh_sys_user")
public class SysUserDO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @TableId
  private Long id;
  /**
   * 头像
   */
  private String avatar;
  /**
   * 名字
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 性别（F 表示女性，M 表示男性）
   */
  private String sex;
  /**
   * 电子邮件
   */
  private String email;
  /**
   * 手机号
   */
  private String mobile;
  /**
   * 国
   */
  private String country;
  /**
   * 省
   */
  private String province;
  /**
   * 市
   */
  private String city;
  /**
   * 用户状态（0：锁定，1正常）
   */
  private Integer accountStatus;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 更新时间
   */
  private LocalDateTime updateTime;
  /**
   * 更新人
   */
  private Long updateUser;

}
