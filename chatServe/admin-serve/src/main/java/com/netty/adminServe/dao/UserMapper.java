package com.netty.adminServe.dao;

import com.netty.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
@Mapper
public interface UserMapper {

    List<User> getAllUser();

    boolean addUser(User user);
}
