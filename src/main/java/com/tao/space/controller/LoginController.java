package com.tao.space.controller;

import com.tao.commons.result.ResponseResult;
import com.tao.space.service.user.LoginService;
import com.tao.space.service.user.dto.LoginReq;
import com.tao.space.service.user.dto.UserAddReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @Author：cboss
 * @Date：2023/3/31
 */
@Api(tags = "登录")
@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户登录")
    public ResponseResult<String> login(@Validated @RequestBody LoginReq req) {
        return loginService.login(req);
    }
}