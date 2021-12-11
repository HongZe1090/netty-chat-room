package com.netty.adminServe.controller;

import com.netty.adminServe.service.AuthService;
import com.netty.adminServe.service.Impl.AuthServiceImpl;
import com.netty.common.model.form.AuthBody;
import com.netty.log.annotation.Log;
import com.netty.log.constants.BusinessType;
import com.netty.log.constants.OperatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/7
 * @描述 权限接口 登陆登出
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/login")
//    @ApiOperation("登录授权")
    @Log(title = "auth",businessType = BusinessType.LOGIN,operatorType = OperatorType.USER)
    public Object login(@RequestBody AuthBody authBody) {
        return authService.login(authBody);
    }

}
