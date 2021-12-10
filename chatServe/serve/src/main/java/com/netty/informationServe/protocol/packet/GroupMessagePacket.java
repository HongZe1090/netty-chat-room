package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.commond;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 群消息发送
 */
public class GroupMessagePacket extends Packet {
    private Integer toGroupId;

    private String message;

    private String fileType;

    public Integer getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public Byte getCommand() {
        return commond.GROUP_MESSAGE;
    }
}
