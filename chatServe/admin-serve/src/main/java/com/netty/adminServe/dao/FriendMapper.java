package com.netty.adminServe.dao;

import com.netty.common.model.Result.Friend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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


    /**
     * 添加朋友
     * @param owerId 当前用户id
     * @param friendId 新添加的朋友id
     * @return 返回添加成功状态 0 -> 添加失败， 1->成功
     */
    @Insert("insert into friend_relation(ower_id,friend_id) values(#{arg0},#{arg1}),(#{arg1},#{arg0})")
    int insertFriend(Integer owerId,Integer friendId);

    /**
     * 删除朋友
     * @param owerId 当前用户id
     * @param friendId 要删除的朋友id
     * @return 返回删除成功状态 0 -> 添加失败， 1->成功
     */
    @Delete("delete from friend_relation where (ower_id = #{arg0} and friend_id = #{arg1})")
    int deleteFriend(Integer owerId,Integer friendId);

    //修改没有必要 关联通过主键 主键平常不会改变，因此修改关系不需要
}
