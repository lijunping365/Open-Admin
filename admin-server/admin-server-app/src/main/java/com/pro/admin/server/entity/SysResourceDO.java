package com.pro.admin.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pro.admin.common.enums.CommonStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表
 * 
 * @author karong
 * @email lijunping365@gmail.com
 * @date 2020-11-30 11:44:56
 */
@Data
@TableName("fresh_sys_resource")
public class SysResourceDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源路由
	 */
	private String path;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 是否是启用选项（1 启用，0 未开启，默认未开启）
	 */
	private CommonStatusEnum enableStatus;
}
