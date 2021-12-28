package com.netty.adminServe.service;

import com.netty.common.entity.User;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
public interface UserService {

    List<User> getAllUser();

    boolean addUser(User user);
}
