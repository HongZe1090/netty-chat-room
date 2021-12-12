package com.netty.adminServe.dao;

import com.netty.common.model.Result.Friend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/12
 * @描述 好友接口
 */
@Mapper
public interface FriendMapper {
    /**

     *@描述 获取用户的好友列表

     */
    public List<Friend> getUserFriends(Integer userId);

}
