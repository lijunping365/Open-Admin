package com.pro.admin.server.dto.resp;


import com.pro.admin.server.vo.SysMenuVO;
import com.pro.admin.server.vo.SysResourceVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色表
 *
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-09-03 11:28:02
 */
@Data
public class SysRoleRespDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  private Long id;
  /**
   * 角色名称
   */
  private String name;
  /**
   * 提示
   */
  private String memo;
  /**
   * 排序字段
   */
  private Integer sort;
  /**
   * 角色启用状态
   */
  private Integer enableStatus;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 修改时间
   */
  private LocalDateTime updateTime;
  /**
   * 创建用户
   */
  private Long createUser;
  /**
   * 修改用户
   */
  private Long updateUser;

  /**
   * 菜单列表
   */
  private List<SysMenuVO> menus;

  /**
   * 资源列表
   */
  private List<SysResourceVO> resources;

}
