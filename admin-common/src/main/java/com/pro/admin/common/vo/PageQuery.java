/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：BaseQuery.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */
package com.pro.admin.common.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Base query.
 *
 * @author li
 */
@ApiModel("分页查询参数")
@Data
public class PageQuery implements Serializable {

  private static final long serialVersionUID = 3319698607712846427L;

  /**
   * 当前页
   */
  @ApiParam(value = "当前页", defaultValue = "1", example = "1")
  private Integer current = 1;

  /**
   * 每页条数
   */
  @ApiParam(value = "每页条数", defaultValue = "10", example = "10")
  private Integer pageSize = 10;

  /**
   * 排序
   */
  private String orderBy;

  /**
   * 获取分页对象
   *
   * @return {@link Page<E>}
   */
  public <E> Page<E> page() {
    return new Page<>(this.current, this.pageSize);
  }

}
