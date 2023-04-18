package com.tao.space.user.controller;

import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.user.dto.UserQueryListReq;
import com.tao.space.service.user.dto.UserQueryListRsp;
import com.tao.space.user.entity.User;
import com.tao.space.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cboss
 * @since 2023-04-01
 */
@Api(tags = "用户Mybatis")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    @ApiOperation(value = "分页列表", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->分页列表")
    @PostMapping("/queryList")
    public ResponseResultPage<UserQueryListRsp> queryList(@Validated @RequestBody UserQueryListReq req) {
        return userService.queryList(req);
    }

    @ApiOperation(value = "分页列表B", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->分页列表")
    @PostMapping("/queryListB")
    public ResponseResultPage<User> queryListB(@Validated @RequestBody UserQueryListReq req) {
        return userService.queryListB(req);
    }

    @ApiOperation(value = "add", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->add")
    @PostMapping("/add")
    public ResponseResult<User> add(@Validated @RequestBody User user) {
        userService.save(user);
        return ResponseResult.success();
    }


    @ApiOperation(value = "update", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->update")
    @PostMapping("/update")
    public ResponseResult<User> update(@Validated @RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->用户详情->删除")
    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseResult.success();
    }
}
