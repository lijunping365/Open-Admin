package com.pro.admin.server.entity.common;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lijunping on 2021/12/24
 */
@Data
@TableName("fresh_sys_tag_group")
public class SysTagGroupDO implements Serializable {
    private static final long serialVersionUID = 4697219494694608309L;
    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 副标题
     */
    private String memo;
    /**
     * 排序
     */
    private Integer sort;
}
