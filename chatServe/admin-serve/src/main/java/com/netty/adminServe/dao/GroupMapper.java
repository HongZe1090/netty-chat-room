package com.netty.adminServe.dao;

import com.netty.common.entity.Group;
import com.netty.common.entity.GroupMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
@Mapper
public interface GroupMapper {

    /**

     *@描述 创建群组

     */
    boolean createGroup(Group group);

    /**

     *@描述 加入群组

     */
    boolean addMember(Integer groupId,Integer memberId);

    /**

     *@描述 获得所有的Group信息，不包括组员

     */
    List<Group> getAllGroup();

    /**

     *@描述 根据用户id获得小组id

     */
    List<Integer> getGroupId(Integer id);

    /**

     *@描述 根据用户id获得小组信息

     */
    List<Group> getGroup(Integer id);

    /**

     *@描述 根据小组id获取其群组id及其所有成员

     */
    List<Integer> getAllMember(Integer id);
}
