package com.tao.space.user.service;

import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.user.dto.UserQueryListReq;
import com.tao.space.service.user.dto.UserQueryListRsp;
import com.tao.space.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cboss
 * @since 2023-04-01
 */
public interface IUserService extends IService<User> {

    ResponseResultPage<UserQueryListRsp> queryList(UserQueryListReq req);

    ResponseResultPage<User> queryListB(UserQueryListReq req);
}
