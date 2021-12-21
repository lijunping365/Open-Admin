package com.pro.admin.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lijunping
 */
@ApiModel("日期时间查询参数")
@Data
public class DateTimeQuery implements Serializable {
  private static final long serialVersionUID = -5260392216936380499L;

  /**
   * 开始时间
   */
  @ApiParam(value = "开始时间 格式为 yyyy-MM-dd HH:mm:ss", example = "2020-10-01 12:00:00")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime beginTime;

  /**
   * 结束时间
   */
  @ApiParam(value = "结束时间 格式为 yyyy-MM-dd HH:mm:ss", example = "2020-10-01 12:00:00")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime = LocalDateTime.now();

}
