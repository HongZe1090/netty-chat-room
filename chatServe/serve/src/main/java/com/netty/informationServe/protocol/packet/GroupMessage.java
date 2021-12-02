package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.commond;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 群消息发送
 */
public class GroupMessage extends Packet {
    /**
     * 创建群里需要把群里的Id发过来
     */
    private List<String> userIdList;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public Byte getCommand() {
        return commond.GROUP_MESSAGE;
    }
}
