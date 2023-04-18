package com.tao.space.dal.mapper.custom;

import com.tao.space.service.user.dto.UserQueryListReq;
import com.tao.space.service.user.dto.UserQueryListRsp;

import java.util.List;

/**
 * @Author：cboss
 * @Date：2023/3/30
 */
public interface CustomSysUserMapper {
    List<UserQueryListRsp> queryList(UserQueryListReq req);
}