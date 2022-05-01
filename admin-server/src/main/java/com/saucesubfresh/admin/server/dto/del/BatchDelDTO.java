package com.saucesubfresh.admin.server.dto.del;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
public class BatchDelDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id 集合
   */
  @NotEmpty(message = "编号不能为空")
  private List<Long> ids;

}
