package com.saucesubfresh.admin.server.dto.req;


import com.saucesubfresh.admin.common.vo.PageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysUserReqDTO extends PageQuery {
  private static final long serialVersionUID = 1L;
  /**
   * 名字
   */
  private String username;
  /**
   * 性别（F 表示女性，M 表示男性）
   */
  private String sex;
  /**
   * 手机号
   */
  private String mobile;
  /**
   * 开始时间
   */
  private LocalDateTime beginTime;
  /**
   * 结束时间
   */
  private LocalDateTime endTime;
  /**
   * 状态(0 表示已删除，1 表示可用，2 表示已冻结)
   */
  private Integer accountStatus;

}
