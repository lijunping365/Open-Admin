package com.saucesubfresh.admin.server.dto.create;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysUserCreateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
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
  @NotEmpty(message = "手机号不能为空")
  private String mobile;
  /**
   * 角色id，逗号分割字符串
   */
  private String roles;
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
   * 状态(0 表示已删除，1 表示可用，2 表示已冻结)
   */
  private Integer accountStatus;

}
