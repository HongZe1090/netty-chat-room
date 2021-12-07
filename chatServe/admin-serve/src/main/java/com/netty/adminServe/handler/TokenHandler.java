package com.netty.adminServe.handler;

import com.netty.common.entity.User;
import org.springframework.stereotype.Component;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/7
 * @描述 关于token生成，储存，刷新的相关的操作
 */
@Component
public class TokenHandler {
    public User saveToken(User user){
        return user;
    }
}
