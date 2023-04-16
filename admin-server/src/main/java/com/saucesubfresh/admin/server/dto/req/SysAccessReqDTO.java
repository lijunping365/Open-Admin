package com.saucesubfresh.admin.server.dto.req;


import com.saucesubfresh.admin.common.vo.PageQuery;
import lombok.Data;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysAccessReqDTO extends PageQuery {
  private static final long serialVersionUID = 1L;

  /**
   * 权限名称
   */
  private String name;
  /**
   * 请求路径
   */
  private String path;

}
