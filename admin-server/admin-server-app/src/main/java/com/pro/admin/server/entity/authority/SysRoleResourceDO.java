package com.pro.admin.server.entity.authority;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:55
 */
@Data
@TableName("fresh_sys_role_resource")
public class SysRoleResourceDO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 角色编号
	 */
	private Long roleId;
	/**
	 * 资源编号
	 */
	private Long resourceId;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private Long createUser;

}
