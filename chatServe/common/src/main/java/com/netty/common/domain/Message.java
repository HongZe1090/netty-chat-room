package com.netty.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/10
 * @描述 返回前端的消息主题与消息队列中储存的消息
 */
public class Message {
    // 发送方id
    private Integer fromId;

    // 接收方id
    private Integer toId;

    // 消息内容
    private String infoContent;

    // 消息类型 在线或离线
    private String type;

    // 消息状态 在线或离线 1为私聊 0为群聊
    private Boolean state;

    //发送时间
    private Date time;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
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
