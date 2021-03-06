package com.netty.adminServe.controller;

import com.netty.adminServe.service.FriendService;
import com.netty.adminServe.service.GroupService;
import com.netty.common.entity.Group;
import com.netty.common.model.base.RestMsg;
import com.netty.common.model.form.AuthBody;
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
    GroupService groupService;

    /**

     *@描述 获取群组列表

     */
    @PostMapping("/getGroup")
    @Log(title = "getGroup",businessType = BusinessType.OTHER,operatorType = OperatorType.USER)
    public Object getGroup(@RequestParam Integer userId) {
        return RestMsg.ok(groupService.getGroup(userId));
    }

    /**

     *@描述 添加群组

     */
    @PostMapping("/addGroup")
    @Log(title = "addGroup",businessType = BusinessType.INSERT,operatorType = OperatorType.USER)
    public Object addGroup(@RequestBody Group group) {
        return RestMsg.ok(groupService.addGroup(group));
    }

    /**

     *@描述 添加群组成员

     */
    @PostMapping("/addGroupMember")
    @Log(title = "addGroupMember",businessType = BusinessType.INSERT,operatorType = OperatorType.USER)
    public Object addGroupMember(@RequestParam Integer group_id,@RequestParam Integer member_id) {
        return RestMsg.ok(groupService.addGroupMember(group_id,member_id));
    }

    /**

     *@描述 获取所有群组列表

     */
    @GetMapping("/getAllGroup")
    @Log(title = "getAllGroup",businessType = BusinessType.OTHER,operatorType = OperatorType.USER)
    public Object getAllGroup() {
        return RestMsg.ok(groupService.getAllGroup());
    }
}
