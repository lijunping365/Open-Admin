package com.pro.starter.security.service;

import java.util.List;
import java.util.Map;

/**
 * @author: 李俊平
 * @Date: 2021-12-23 23:48
 */
public interface AuthorityService {

    Map<String, List<String>> getAuthorities();

}
