package com.tao.space.service.user;

import com.tao.commons.result.ResponseResult;
import com.tao.space.service.user.dto.LoginReq;

/**
 * @Author：cboss
 * @Date：2023/3/31
 */
public interface LoginService {
    ResponseResult login(LoginReq req);
}
