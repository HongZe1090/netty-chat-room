package com.netty.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
public class GroupMember implements Serializable {
    private static final long serialVersionUID = 478123456;

    Integer groupId;

    List<Integer> memberId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getMemberId() {
        return memberId;
    }

    public void setMemberId(List<Integer> memberId) {
        this.memberId = memberId;
    }
}
