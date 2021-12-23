package com.pro.starter.security.service;


import com.pro.starter.security.domain.Authentication;

/**
 * @author lijunping on 2021/10/22
 */
public interface TokenService {

    Authentication readAuthentication(String accessToken);
}
