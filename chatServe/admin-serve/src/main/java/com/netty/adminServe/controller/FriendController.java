package com.netty.adminServe.controller;

import com.netty.adminServe.dao.FriendMapper;
import com.netty.adminServe.service.FriendService;
import com.netty.common.model.base.RestMsg;
import com.netty.common.model.form.AuthBody;
import com.netty.log.annotation.Log;
import com.netty.log.constants.BusinessType;
import com.netty.log.constants.OperatorType;
import org.junit.runners.Parameterized;
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
    FriendService friendService;

    /**

     *@描述 获取好友列表

     */
    @PostMapping("/getAllFriends")
    @Log(title = "getFriend",businessType = BusinessType.OTHER,operatorType = OperatorType.USER)
    public Object getAllFriends(@RequestParam Integer userId) {
        System.out.println(userId);
//        return "success";
        return RestMsg.ok(friendService.getUserFriends(userId));
    }

    @GetMapping("/addFriend")
    @Log(title = "addFriend",businessType = BusinessType.INSERT,operatorType = OperatorType.USER)
    public RestMsg addFriend(@RequestParam Integer userId, @RequestParam Integer friendId){
        int status = friendService.addFriend(userId, friendId);
        if(status == 0){
            return RestMsg.ok("添加成功");
        }else {
            return RestMsg.fail("异常添加失败，请重试");
        }
    }

    @GetMapping("/deleteFriend")
    @Log(title = "deleteFriend",businessType = BusinessType.DELETE,operatorType = OperatorType.USER)
    public RestMsg deleteFriend(@RequestParam Integer userId, @RequestParam Integer friendId){
        int status = friendService.deleteFriend(userId, friendId);
        if(status == 0){
            return RestMsg.ok("单向删除");
        }else {
            return RestMsg.fail("删除失败，请重试");
        }
    }




}
