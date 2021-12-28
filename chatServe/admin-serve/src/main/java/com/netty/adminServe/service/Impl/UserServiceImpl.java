package com.netty.adminServe.service.Impl;

import com.netty.adminServe.dao.UserMapper;
import com.netty.adminServe.service.UserService;
import com.netty.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }
}
