package com.tao.space.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tao.commons.result.ResponseResultPage;
import com.tao.commons.redisson.RedisLock;
import com.tao.commons.utils.BeanUtils;
import com.tao.space.common.constant.RedisKeyConstant;
import com.tao.space.common.constant.RedissonLockKeyConstants;
import com.tao.space.dal.mapper.SysUserMapper;
import com.tao.space.dal.mapper.custom.CustomSysUserMapper;
import com.tao.space.dal.model.SysUser;
import com.tao.space.service.user.SysUserService;
import com.tao.space.service.user.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author：cboss
 * @Date：2023/3/25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    private final RedisTemplate redisTemplate;
    private final SysUserMapper sysUserMapper;
    private final CustomSysUserMapper customSysUserMapper;

    @Override
    public void add(UserAddReq req) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);
        sysUser.setStatus(Short.valueOf("1"));
        sysUser.setIsDelete(Short.valueOf("2"));
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void update(UserUpdateReq req) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public void delete(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ResponseResultPage<UserQueryListRsp> queryList(UserQueryListReq req) {
        Page<UserQueryListRsp> page = PageHelper.startPage(req.getPage(), req.getSize());
        List<UserQueryListRsp> userQueryListRspList = customSysUserMapper.queryList(req);
        return ResponseResultPage.page(page.getTotal(), userQueryListRspList);
    }

    @Override
    public UserDetailRsp detail(Long id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        return BeanUtils.copyBean(sysUser, UserDetailRsp.class);
    }

    @Override
    public UserDetailRsp detailCache(Long id) {
        String cacheKey = RedisKeyConstant.USER_ID.replace("{id}", String.valueOf(id));
        UserDetailRsp userDetailRsp = (UserDetailRsp) redisTemplate.opsForValue().get(cacheKey);
        if (Objects.isNull(userDetailRsp)) {
            userDetailRsp = this.detail(id);
            redisTemplate.opsForValue().set(cacheKey, userDetailRsp, 12, TimeUnit.HOURS);
        }
        return userDetailRsp;
    }

    @RedisLock(key = RedissonLockKeyConstants.USER_ID)
    @Override
    public void updateDistributedLock(UserUpdateReq req) {
        log.info("start");
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end");
    }
}