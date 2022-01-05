package com.pro.starter.logger.service;

import com.pro.starter.logger.model.LogModel;

/**
 * @author lijunping on 2022/1/5
 */
public interface LoggerRecordService {

    /**
     * 保存 log
     *
     * @param logModel 日志实体
     */
    void record(LogModel logModel);
}
