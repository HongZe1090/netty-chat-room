package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.commond;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 创建群消息
 */
public class CreateGroupPacket extends Packet {
    /**
     * 创建群里需要把群里的Id发过来
     */
    private List<Integer> userIdList;

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public Byte getCommand() {
        return commond.CREATE_GROUP;
    }
}
