package com.netty.adminServe.service.Impl;

import com.netty.adminServe.dao.AuthMapper;
import com.netty.adminServe.handler.TokenHandler;
import com.netty.common.entity.User;
import com.netty.common.model.base.RestMsg;
import com.netty.common.model.form.AuthBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/7
 * @描述
 */
@Service
public class AuthServiceImpl {
    @Autowired
    AuthMapper authMapper;

    @Autowired
    TokenHandler tokenHandler;

    public RestMsg<Object> login(AuthBody authBody){

        User user = authMapper.selectUserInfoByName(authBody.getUserName());
        if (user == null) {
            return RestMsg.fail("帐号不存在");
        }
        if (!user.getPassword().equals(authBody.getPassword())) {
            return RestMsg.fail("密码错误");
        }

        return RestMsg.ok(tokenHandler.saveToken(user));
    }
}
