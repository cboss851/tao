package com.tao.space.service.user;

import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.user.dto.*;

/**
 * @Author：cboss
 * @Date：2023/3/25
 */
public interface SysUserService {
    void add(UserAddReq req);

    void update(UserUpdateReq req);

    void delete(Long id);

    ResponseResultPage<UserQueryListRsp> queryList(UserQueryListReq req);

    UserDetailRsp detail(Long id);

    UserDetailRsp detailCache(Long id);

    void updateDistributedLock(UserUpdateReq req);
}
