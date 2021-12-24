package com.netty.adminServe.service.Impl;

import com.netty.adminServe.dao.FriendMapper;
import com.netty.adminServe.service.FriendService;
import com.netty.common.model.Result.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/12
 * @描述 查找好友列表并筛选
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;

    @Override
    public Set<Friend> getUserFriends(Integer userId) {
        Set<Friend> soleFriends = new HashSet<>();
        List<Friend> friends = friendMapper.getUserFriends(userId);
        for (Friend friend:friends){
            soleFriends.add(friend);
        }
        return soleFriends;
    }

    @Override
    public int addFriend(Integer userId, Integer friendId) {
        return friendMapper.insertFriend(userId, friendId);
    }

    @Override
    public int deleteFriend(Integer userId, Integer friendId) {
        return friendMapper.deleteFriend(userId,friendId);
    }
}
