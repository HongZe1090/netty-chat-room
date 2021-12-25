package com.netty.adminServe.controller;

import com.netty.adminServe.service.FriendService;
import com.netty.common.model.base.RestMsg;
import com.netty.log.annotation.Log;
import com.netty.log.constants.BusinessType;
import com.netty.log.constants.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/25
 * @描述
 */
@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {
    @Autowired
    FriendService friendService;

    /**

     *@描述 获取群组列表

     */
    @PostMapping("/getGroup")
    @Log(title = "getGroup",businessType = BusinessType.OTHER,operatorType = OperatorType.USER)
    public Object getAllFriends(@RequestParam Integer userId) {
        System.out.println(userId);
//        return "success";
        return RestMsg.ok(friendService.getUserFriends(userId));
    }
}
