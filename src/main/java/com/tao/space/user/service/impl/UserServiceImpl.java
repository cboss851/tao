package com.tao.space.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.user.dto.UserQueryListReq;
import com.tao.space.service.user.dto.UserQueryListRsp;
import com.tao.space.user.entity.User;
import com.tao.space.dal.mapper.UserMapper;
import com.tao.space.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cboss
 * @since 2023-04-01
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;

    @Override
    public ResponseResultPage<UserQueryListRsp> queryList(UserQueryListReq req) {
        Page<User> pageC = new Page(req.getPage(), req.getSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        Page page = userMapper.selectPage(pageC, queryWrapper);
        return ResponseResultPage.page(page.getTotal(), page.getRecords());
    }

    @Override
    public ResponseResultPage<User> queryListB(UserQueryListReq req) {
        com.github.pagehelper.Page<User> page = PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userQueryListRspList = userMapper.queryList(req);
        return ResponseResultPage.page(page.getTotal(), userQueryListRspList);
    }

}
