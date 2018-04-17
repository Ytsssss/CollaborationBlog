package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.vo.UserChangeVO;

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

    /**
     * 注册账号
     * @param accountId
     * @param password
     * @param verifyCode 邮箱验证码
     * @param mailAddress 邮箱
     * @return userId
     */
    Long register(String accountId, String password, String verifyCode, String mailAddress);

    /**
     *  通过userId 获取token
     * @param userId
     * @return
     */
    String getTokenByUser(Long userId);

    /**
     * 通过token获得用户信息
     * @param token
     * @return
     */
    User getUserByToken(String token);

    int changeUserInfo(UserChangeVO user, String token);
}
