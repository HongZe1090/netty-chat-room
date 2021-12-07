package com.netty.adminServe.service;

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
public interface AuthService {

//    登陆方法
    public RestMsg<Object> login(AuthBody authBody);
}
