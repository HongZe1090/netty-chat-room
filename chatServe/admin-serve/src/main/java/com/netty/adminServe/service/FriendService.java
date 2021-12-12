package com.netty.adminServe.service;

import com.netty.common.model.Result.Friend;

import java.util.Set;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/12
 * @描述 好友相关接口
 */
public interface FriendService {

    public Set<Friend> getUserFriends(Integer userId);
}
