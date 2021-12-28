package com.netty.adminServe.service;

import com.netty.common.entity.Group;
import com.netty.common.model.Result.GroupInfo;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
public interface GroupService {
    /**

     *@描述 获得用户的群组信息

     */
    List<GroupInfo> getGroup(Integer userId);
    /**

     *@描述 获得所有的群组信息

     */
    List<Group> getAllGroup();
    /**

     *@描述 添加群组信息

     */
    boolean addGroup(Group group);

    /**

     *@描述 添加群组成员

     */
    boolean addGroupMember(Integer group_id,Integer member_id);
}
