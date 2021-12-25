package com.netty.adminServe.controller;

import com.netty.adminServe.service.Impl.LogServiceImpl;
import com.netty.adminServe.service.Impl.MessageServiceImpl;
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
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;

    @GetMapping("/getAllInfo")
    @Log(title = "getAllMessage",businessType = BusinessType.OTHER,operatorType = OperatorType.ADMIN)
    public Object getAllMessage() {
        return RestMsg.ok(messageService.getAllInfo());
    }

    @GetMapping("/getInfo")
    @Log(title = "getMessage",businessType = BusinessType.OTHER,operatorType = OperatorType.ADMIN)
    public Object getMessage(@RequestParam Integer from, @RequestParam Integer toId) {
        System.out.println(from);
        return RestMsg.ok(messageService.getMessage(from,toId));
    }

}
