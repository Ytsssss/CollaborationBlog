package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.UserMapper;
import com.ytsssss.collaborationblog.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/20 14:07
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserInfo(String accountId, String password) {
        return null;
    }

    @Override
    public User getUserInfo(Long userId) {
        return null;
    }

    @Override
    public Long login(String accountId, String password) {
        Long userId = userMapper.selectByaccountIdAndPassword(accountId, password);
        if (userId == null || "".equals(userId)){
            return -1L;
        }
        return userId;
    }
}
