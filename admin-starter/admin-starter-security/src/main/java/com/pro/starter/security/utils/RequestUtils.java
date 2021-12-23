package com.pro.starter.security.utils;


import javax.servlet.http.HttpServletRequest;

import static com.pro.oauth.common.constants.CommonConstant.AUTHENTICATION_HEADER;
import static com.pro.oauth.common.constants.CommonConstant.AUTHENTICATION_TYPE;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: HttpServletRequest 工具类
 */
public class RequestUtils {

    /**
     * @param request
     * @return
     *
     * @throws SecurityException
     */
    public static String extractTokenFromHeader(HttpServletRequest request) throws SecurityException {
        String header = request.getHeader(AUTHENTICATION_HEADER);
        if (header == null || !header.startsWith(AUTHENTICATION_TYPE)) {
            throw new SecurityException("错误的请求头");
        }
        return header.substring(7);
    }
}
