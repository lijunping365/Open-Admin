package com.pro.admin.server.convert;

import com.pro.admin.server.dto.req.SysMobileOAuthLoginReqDTO;
import com.pro.admin.server.dto.req.SysPasswordOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.MobileOAuthLoginReqDTO;
import com.pro.oauth.server.dto.req.PasswordOAuthLoginReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lijunping on 2022/1/14
 */
@Mapper
public interface SysOAuthLoginConvert {

    SysOAuthLoginConvert INSTANCE = Mappers.getMapper(SysOAuthLoginConvert.class);

    PasswordOAuthLoginReqDTO convertPasswordDTO(SysPasswordOAuthLoginReqDTO request);

    MobileOAuthLoginReqDTO convertMobileDTO(SysMobileOAuthLoginReqDTO request);
}
