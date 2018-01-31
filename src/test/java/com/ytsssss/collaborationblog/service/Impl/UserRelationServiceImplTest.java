package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.service.UserRelationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/31 14:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRelationServiceImplTest {

    @Autowired
    private UserRelationService userRelationService;
    @Test
    public void addUserAttention() {
        userRelationService.addUserAttention(9L, 10L);
    }

    @Test
    public void cancelUserAttention() {
        userRelationService.cancelUserAttention(9L, 10L);
    }

    @Test
    public void getFansList() {
        userRelationService.getFansList(10L);
    }

    @Test
    public void getAttentionList() {
        userRelationService.getAttentionList(9L);
    }

    @Test
    public void addUserFriend() {
        userRelationService.addUserFriend(9L, 10L);
    }

    @Test
    public void deleteUserFriend() {
        userRelationService.deleteUserFriend(9L, 10L);
    }
    @Test
    public void confirmFriend(){
        userRelationService.confirmUserFriend(9L, 10L, 0);
    }
    @Test
    public void getfriendList(){
        userRelationService.getUserFriendList(10L);
    }
    @Test
    public void getquestList(){
        userRelationService.getQuestFriendList(10L);
    }
}