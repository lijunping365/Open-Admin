package com.saucesubfresh.admin.server.dto.update;


import com.saucesubfresh.admin.common.enums.CommonStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 管理员表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 17:22:44
 */
@Data
public class SysUserUpdateDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @NotEmpty(message = "用户编号不能为空")
  private List<Long> userIds;

  /**
   * 账号状态(0 表示已删除，1 表示可用，2 表示已冻结)
   */
  @NotNull(message = "账号状态不能为空")
  private Integer accountStatus;

}
