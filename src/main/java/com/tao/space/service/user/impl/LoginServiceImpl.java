package com.tao.space.service.user.impl;

import com.tao.commons.result.ResponseResult;
import com.tao.commons.utils.JWTUtils;
import com.tao.space.service.user.LoginService;
import com.tao.space.service.user.dto.LoginReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author：cboss
 * @Date：2023/3/31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    /**
     * 登录
     *
     * @param req
     * @return token
     */
    @Override
    public ResponseResult login(LoginReq req) {
        if ("admin".equalsIgnoreCase(req.getUsername()) && "123456".equalsIgnoreCase(req.getPassword())) {
            String token = JWTUtils.getTokenByUserId("123456789");
            return ResponseResult.successData(token);
        } else {
            return ResponseResult.failMsg("");
        }
    }
}
