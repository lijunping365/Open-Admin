package com.pro.starter.logger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lijunping on 2022/1/5
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogModel implements Serializable {
    private static final long serialVersionUID = -6790223689623037179L;

    /**
     * 用户 id
     */
    private Long userId;
    /**
     * 访问地址
     */
    private String uri;
    /**
     * 参数
     */
    private String queryString;
    /**
     * ip
     */
    private String ip;
    /**
     * 操作状态
     */
    private Integer status;
    /**
     * 操作状态
     */
    private LocalDateTime createTime;
}
