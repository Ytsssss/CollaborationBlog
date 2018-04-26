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
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.FollowAttListVO;
import com.ytsssss.collaborationblog.vo.UserFriendVO;
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
        return userFriendMapper.deleteByUserAndFriendId(userId, friendId);
    }

    @Override
    public int confirmUserFriend(Long userId, Long friendId, int status) {
        UserFriend userFriend = new UserFriend();
        userFriend.setFriendId(userId);
        userFriend.setStatus(0);
        userFriend.setCreateTime(new Date());
        userFriend.setUpdateTime(new Date());
        userFriend.setUserId(friendId);
        userFriendMapper.insertSelective(userFriend);
        return userFriendMapper.confirmUserFriend(userId, friendId, status);
    }

    @Override
    public List<UserFriendVO> getUserFriendList(Long userId) {
        List<Long> friendList = userFriendMapper.getUserFriendList(userId);
        logger.info(friendList.toString());
        List<UserFriendVO> userFriendVOList = getFriendList(friendList, 0);
        return userFriendVOList;
    }

    @Override
    public List<UserFriendVO> getQuestFriendList(Long userId) {
        List<Long> questIdList = userFriendMapper.getQuestFriendList(userId);
        logger.info(questIdList.toString());
        List<UserFriendVO> userFriendVOList = getFriendList(questIdList, 1);
        return userFriendVOList;
    }

    @Override
    public List<FollowAttListVO> getEachList(Long userId) throws Exception {
        logger.info(userAttentionMapper.getUserAttentionList(userId).toString());
        List<Long> attentionIdList = userAttentionMapper.getUserAttentionList(userId);
        if (attentionIdList == null){
            throw new GlobalException(GlobalResultStatus.PARAM_ERROR);
        }
        List<FollowAttListVO> attListVOList = getFollowAttVo(userId, attentionIdList);
        List<FollowAttListVO> eachList = new ArrayList<>();
        for (FollowAttListVO followAttListVO : attListVOList){
            if (followAttListVO.isFollow()){
                eachList.add(followAttListVO);
            }
        }
        return eachList;
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

    private List<UserFriendVO> getFriendList(List<Long> list, int type){
        List<UserFriendVO> userFriendVOList = new ArrayList<>();
        for (Long id : list){
            UserFriendVO userFriendVO = new UserFriendVO();
            UserFriend userFriend = userFriendMapper.selectByPrimaryKey(id);
            userFriendVO.setFriendId(userFriend.getFriendId());
            userFriendVO.setCreateTime(TimeUtil.changeTimeToString(userFriend.getCreateTime()));
            userFriendVO.setId(id);
            userFriendVO.setStatus(userFriend.getStatus());
            userFriendVO.setUserId(userFriend.getUserId());
            Long friendId;
            if (type == 0){
                friendId = userFriend.getFriendId();
            }else{
                friendId = userFriend.getUserId();
            }
            User user = userService.getUserInfo(friendId);
            userFriendVO.setAvatar(user.getAvatar());
            userFriendVO.setUserName(user.getName());
            userFriendVO.setSchool(user.getSchool());
            userFriendVO.setIntroduce(user.getIntroduce());
            userFriendVOList.add(userFriendVO);
        }
        return userFriendVOList;
    }
}
