package com.netty.informationServe.protocol.packet;

import com.netty.common.domain.User;
import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.commond;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 用户注册包
 */
public class RegisterPacket extends Packet{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public Byte getCommand() {
        return commond.REGISTER;
    }
}
