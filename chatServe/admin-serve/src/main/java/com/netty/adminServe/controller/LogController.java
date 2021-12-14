package com.netty.adminServe.controller;

import com.netty.adminServe.service.Impl.LogServiceImpl;
import com.netty.common.model.base.RestMsg;
import com.netty.log.annotation.Log;
import com.netty.log.constants.BusinessType;
import com.netty.log.constants.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/14
 * @描述 日志接口
 */
@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogServiceImpl logService;
    /**

     *@描述 获取日志列表

     */
    @GetMapping("/getAllLog")
    @Log(title = "getLog",businessType = BusinessType.OTHER,operatorType = OperatorType.ADMIN)
    public Object getLog() {
        return RestMsg.ok(logService.getAllLog());
    }


}
