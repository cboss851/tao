package com.tao.space.controller;

import com.tao.space.service.user.dto.UserAddReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.commons.result.ResponseResult;

/**
 * Response示例
 *
 * @Author：cboss
 * @Date：2023/3/25 21:34
 */
@Api(tags = "Response示例")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/response")
public class ResponseController {

    @ApiOperation("success()")
    @GetMapping("/success")
    public ResponseResult success() {
        return ResponseResult.success();
    }

    @ApiOperation("successMsg(String message)")
    @GetMapping("/successMsg")
    public ResponseResult<String> successMsg() {
        return ResponseResult.successMsg("good");
    }

    @ApiOperation("successData(Object data)")
    @GetMapping("/successData")
    public ResponseResult<String> successData() {
        return ResponseResult.successData("good");
    }

    @ApiOperation("success(String message, Object data)")
    @GetMapping("/successD")
    public ResponseResult successD() {
        UserAddReq userAddReq = UserAddReq.builder()
                .username("cboss")
                .phone("15120399390")
                .build();
        return ResponseResult.success("good", userAddReq);
    }

    @ApiOperation("fail()")
    @GetMapping("/fail")
    public ResponseResult fail() {
        return ResponseResult.fail();
    }

    @ApiOperation("failMsg(String message)")
    @GetMapping("/failMsg")
    public ResponseResult<String> failB() {
        return ResponseResult.failMsg("失败信息");
    }

    @ApiOperation("failData(Object data)")
    @GetMapping("/failData")
    public ResponseResult<String> failC() {
        return ResponseResult.failData("失败数据");
    }

    @ApiOperation("fail(String message, Object data)")
    @GetMapping("/failD")
    public ResponseResult failD() {
        UserAddReq userAddReq = UserAddReq.builder()
                .username("cboss")
                .phone("15120399390")
                .build();
        return ResponseResult.fail("失败信息", userAddReq);
    }

}
