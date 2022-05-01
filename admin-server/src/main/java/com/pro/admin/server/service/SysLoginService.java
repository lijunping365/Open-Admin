package com.pro.admin.server.service;

import com.pro.admin.server.dto.req.SysMobileOAuthLoginReqDTO;
import com.pro.admin.server.dto.req.SysPasswordOAuthLoginReqDTO;
import com.pro.admin.server.dto.resp.SysOAuthLoginRespDTO;

/**
 * @author lijunping on 2022/1/14
 */
public interface SysLoginService {

    SysOAuthLoginRespDTO loginByUsername(SysPasswordOAuthLoginReqDTO request);

    SysOAuthLoginRespDTO loginByMobile(SysMobileOAuthLoginReqDTO request);
}
