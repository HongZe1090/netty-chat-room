package com.netty.adminServe.dao;

import com.netty.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/7
 * @描述 权限接口
 */
@Mapper
public interface AuthMapper {

    User selectUserInfoByName(String userName);

}
