package com.netty.adminServe.controller;

import com.netty.adminServe.dao.FriendMapper;
import com.netty.common.model.base.RestMsg;
import com.netty.common.model.form.AuthBody;
import com.netty.log.annotation.Log;
import com.netty.log.constants.BusinessType;
import com.netty.log.constants.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/12
 * @描述 好友接口(单个)
 */
@RestController
@CrossOrigin
@RequestMapping("/userFriend")
public class FriendController {
    @Autowired
    FriendMapper friendMapper;

    /**

     *@描述 获取好友列表

     */
    @PostMapping("/getAllFriends")
    @Log(title = "getFriend",businessType = BusinessType.OTHER,operatorType = OperatorType.USER)
    public Object login(@RequestParam Integer userId) {
        System.out.println(userId);
        return RestMsg.ok(friendMapper.getUserFriends(userId));
    }


}
