package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.UserAttention;
import com.ytsssss.collaborationblog.domain.UserFriend;
import com.ytsssss.collaborationblog.mapper.UserAttentionMapper;
import com.ytsssss.collaborationblog.mapper.UserFriendMapper;
import com.ytsssss.collaborationblog.service.UserRelationService;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/31 14:27
 */
@Service
public class UserRelationServiceImpl implements UserRelationService{
    private static Logger logger = LoggerFactory.getLogger(UserRelationServiceImpl.class);
    @Resource
    private UserAttentionMapper userAttentionMapper;
    @Resource
    private UserFriendMapper userFriendMapper;
    @Override
    public int addUserAttention(Long userId, Long attentionId) {
        UserAttention userAttention = new UserAttention();
        userAttention.setAttentionId(attentionId);
        userAttention.setUserId(userId);
        return userAttentionMapper.insertSelective(userAttention);
    }

    @Override
    public int cancelUserAttention(Long userId, Long attentionId) {
        return userAttentionMapper.deleteByUserAndAttentionId(userId, attentionId);
    }

    @Override
    public List<Long> getFansList(Long userId) {
        logger.info(userAttentionMapper.getUserFansList(userId).toString());
        return userAttentionMapper.getUserFansList(userId);
    }

    @Override
    public List<Long> getAttentionList(Long userId) {
        logger.info(userAttentionMapper.getUserAttentionList(userId).toString());
        return userAttentionMapper.getUserAttentionList(userId);
    }

    @Override
    public int addUserFriend(Long userId, Long friendId) {
        UserFriend userFriend = new UserFriend();
        userFriend.setFriendId(friendId);
        userFriend.setUserId(userId);
        userFriend.setStatus(1);
        return userFriendMapper.insertSelective(userFriend);
    }

    @Override
    public int deleteUserFriend(Long userId, Long friendId) {
        userFriendMapper.deleteByUserAndFriendId(friendId, userId);
        return userFriendMapper.deleteByUserAndFriendId(userId, friendId);
    }

    @Override
    public int confirmUserFriend(Long userId, Long friendId, int status) {
        UserFriend userFriend = new UserFriend();
        userFriend.setFriendId(userId);
        userFriend.setUserId(friendId);
        userFriend.setStatus(0);
        userFriendMapper.insertSelective(userFriend);
        return userFriendMapper.confirmUserFriend(userId, friendId, status);
    }

    @Override
    public List<Long> getUserFriendList(Long userId) {
        logger.info(userFriendMapper.getUserFriendList(userId).toString());
        return userFriendMapper.getUserFriendList(userId);
    }

    @Override
    public List<Long> getQuestFriendList(Long userId) {
        logger.info(userFriendMapper.getQuestFriendList(userId).toString());
        return userFriendMapper.getQuestFriendList(userId);
    }
}
