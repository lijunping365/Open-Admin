package com.pro.admin.server.logger.service;

import com.pro.starter.logger.model.UserModel;
import com.pro.starter.logger.service.UserService;
import com.pro.starter.security.context.UserSecurityContext;
import com.pro.starter.security.context.UserSecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lijunping on 2022/1/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserModel getUserModel() {
        UserSecurityContext context = UserSecurityContextHolder.getContext();
        return Optional.ofNullable(context)
                .map(a -> new UserModel(a.getId(), a.getUsername(), a.getMobile()))
                .orElseThrow(()->new IllegalArgumentException("user is null"));
    }
}
