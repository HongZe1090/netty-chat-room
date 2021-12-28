package com.netty.adminServe.controller;

import com.netty.adminServe.service.Impl.MessageServiceImpl;
import com.netty.adminServe.service.Impl.UserServiceImpl;
import com.netty.common.entity.Group;
import com.netty.common.entity.User;
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
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /**

     *@描述 获取用户列表

     */
    @GetMapping("/getUser")
    @Log(title = "getUser",businessType = BusinessType.OTHER,operatorType = OperatorType.ADMIN)
    public Object getLog() {
        return RestMsg.ok(userService.getAllUser());
    }

    /**

     *@描述 添加用户

     */
    @PostMapping("/addUser")
    @Log(title = "addUser",businessType = BusinessType.INSERT,operatorType = OperatorType.ADMIN)
    public Object addUser(@RequestBody User user) {
        return RestMsg.ok(userService.addUser(user));
    }
}
