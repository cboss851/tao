package com.tao.space.dal.mapper;

import com.tao.space.service.user.dto.UserQueryListReq;
import com.tao.space.service.user.dto.UserQueryListRsp;
import com.tao.space.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cboss
 * @since 2023-04-01
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> queryList(UserQueryListReq req);
}
