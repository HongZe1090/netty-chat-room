package com.netty.adminServe.service.Impl;

import com.netty.adminServe.dao.GroupMapper;
import com.netty.adminServe.service.GroupService;
import com.netty.common.entity.Group;
import com.netty.common.model.Result.GroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
@Service
public class GroupInfoImpl implements GroupService {
    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<GroupInfo> getGroup(Integer userId) {
        List<Integer> groupId = groupMapper.getGroupId(userId);
        List<GroupInfo> groupInfos = new ArrayList<>();
        for(Integer id:groupId) {
            GroupInfo info = new GroupInfo();
            Group group = groupMapper.getGroup(id);
            info.setGroupId(group.getGroupId());
            info.setDescription(group.getDescription());
            info.setGroupName(group.getGroupName());
            info.setImage(group.getImage());
            info.setAdmin(group.getAdmin());
            info.setMemberId(groupMapper.getAllMember(id));
            groupInfos.add(info);
        }
        return groupInfos;
    }

    @Override
    public List<Group> getAllGroup() {
        return groupMapper.getAllGroup();
    }

    @Override
    public boolean addGroup(Group group) {
        return groupMapper.createGroup(group);
    }

    @Override
    public boolean addGroupMember(Integer group_id, Integer member_id) {
        return groupMapper.addMember(group_id,member_id);
    }
}
