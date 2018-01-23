package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.UserMapper;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.SHAUtil;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/20 14:07
 */
@Service
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserInfo(String accountId, String password) {
        User user = userMapper.getUserInfoByIdAndPassword(accountId, password);
        return user;
    }

    @Override
    public User getUserInfo(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Long login(String accountId, String password) {
        //使用SHA 进行密码加密
        String encode = SHAUtil.getResult(password);
        Long userId = userMapper.selectByaccountId(accountId);
        if (userId == null || "".equals(userId)){
            return -2L;
        }
        // 如果账号存在，查看账号密码是否正确
        userId = userMapper.selectByaccountIdAndPassword(accountId, encode);
        if (userId == null || "".equals(userId)){
            return -1L;
        }
        return userId;
    }

    @Override
    public Long register(String accountId, String password){
        Long userId = userMapper.selectByaccountId(accountId);
        if (userId != null){
            return -1L;
        }
        //使用SHA 进行密码加密
        String encode = SHAUtil.getResult(password);

        User user = new User();
        user.setaccountId(accountId);
        user.setPassword(encode);
        user.setName(accountId);

        return (long)userMapper.insertSelective(user);
    }
}
