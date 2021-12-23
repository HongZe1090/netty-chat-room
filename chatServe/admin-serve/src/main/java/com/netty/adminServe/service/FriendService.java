package com.netty.adminServe.service;

import com.netty.common.model.Result.Friend;

import java.util.Set;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/12
 * @描述 好友相关接口
 */
public interface FriendService {

    /**
     * 获取当前用户朋友列表
     * @param userId
     * @return
     */
    public Set<Friend> getUserFriends(Integer userId);

    /**
     * 添加好友
     * @param userId
     * @param friendId
     * @return
     */
    int addFriend(Integer userId, Integer friendId);

    /**
     * 删除好友
     * @param userId
     * @param friendId
     * @return
     */
    int deleteFriend(Integer userId, Integer friendId);


}
