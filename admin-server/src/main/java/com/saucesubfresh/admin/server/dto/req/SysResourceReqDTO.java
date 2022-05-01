package com.saucesubfresh.admin.server.dto.req;


import com.saucesubfresh.admin.common.vo.DateTimePageQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@Data
public class SysResourceReqDTO extends DateTimePageQuery implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 资源名称
   */
  private String name;
  

}
