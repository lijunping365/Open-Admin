package com.saucesubfresh.admin.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 李俊平
 * @Date: 2023-04-16 11:10
 */
@Data
@TableName("open_admin_access")
public class SysAccessDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -5683690415469557560L;
    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 请求路径
     */
    private String path;
}
