package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.User;
import org.springframework.stereotype.Service;
import sun.nio.cs.US_ASCII;

/**
 * Create by Ytsssss on 2018/1/19 14:43
 */
public interface UserService {

    /**
     * 根据用户名密码获取用户信息
     * @param accountId
     * @param password
     * @return user对象
     */
    User getUserInfo(String accountId, String password);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    User getUserInfo(Long userId);

    /**
     * 根据用户名密码判断用户是否存在
     * @param accountId
     * @param password
     * @return userId
     */
    Long login(String accountId, String password);
}
