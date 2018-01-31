package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/23 16:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Test
    public void getUserInfo() {
    }

    @Test
    public void getUserInfo1() {
    }

    @Test
    public void login() {
    }

    @Test
    public void register() {
        userService.register("lianghongcheng","123456","97ec74","592625054@qq.com");
    }

    @Test
    public void getTokenByUser(){
        userService.getTokenByUser(9L);

    }
    @Test
    public void getUserByToken(){
        userService.getUserByToken("a5ac1524-6305-44d3-8");
    }

}