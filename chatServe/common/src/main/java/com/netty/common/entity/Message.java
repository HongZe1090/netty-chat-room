package com.netty.common.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/10
 * @描述
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 478123456;

    // 发送方id
    private Integer fromId;

    // 接收方id
    private Integer toId;

    // 消息内容
    private String infoContent;

    // 消息类型
    private String type;

    //发送时间
    private Date time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
