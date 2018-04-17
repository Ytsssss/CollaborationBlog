package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.domain.UserAttention;
import com.ytsssss.collaborationblog.domain.UserFriend;
import com.ytsssss.collaborationblog.exception.GlobalException;
import com.ytsssss.collaborationblog.mapper.UserAttentionMapper;
import com.ytsssss.collaborationblog.mapper.UserFriendMapper;
import com.ytsssss.collaborationblog.service.UserRelationService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.vo.FollowAttListVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

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
    public List<FollowAttListVO> getFansList(Long userId) throws Exception{
        logger.info(userAttentionMapper.getUserFansList(userId).toString());
        List<Long> fansIdList = userAttentionMapper.getUserFansList(userId);
        if (fansIdList == null){
            throw new GlobalException(GlobalResultStatus.PARAM_ERROR);
        }
        List<FollowAttListVO> followListVOList = getFollowAttVo(userId, fansIdList);
        return followListVOList;
    }

    @Override
    public List<FollowAttListVO> getAttentionList(Long userId) throws Exception{
        logger.info(userAttentionMapper.getUserAttentionList(userId).toString());
        List<Long> attentionIdList = userAttentionMapper.getUserAttentionList(userId);
        if (attentionIdList == null){
            throw new GlobalException(GlobalResultStatus.PARAM_ERROR);
        }
        List<FollowAttListVO> attListVOList = getFollowAttVo(userId, attentionIdList);
        return attListVOList;
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

    private List<FollowAttListVO> getFollowAttVo(Long userId, List<Long> userList){
        List<FollowAttListVO> followAttListVOList = new ArrayList<>();
        for (Long id : userList){
            User user = userService.getUserInfo(id);
            FollowAttListVO followAttListVO = new FollowAttListVO();
            followAttListVO.setUserId(id);
            followAttListVO.setAvatar(user.getAvatar());
            followAttListVO.setSchool(user.getSchool());
            followAttListVO.setName(user.getName());
            followAttListVO.setLocation(user.getLocation());
            followAttListVO.setIntroduce(user.getIntroduce());
            int isFollow = userAttentionMapper.isAttention(userId, id);
            followAttListVO.setFollow(isFollow == 1);
            followAttListVOList.add(followAttListVO);
        }
        return followAttListVOList;
    }
}
