package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.domain.UserMessage;
import com.ytsssss.collaborationblog.exception.GlobalException;
import com.ytsssss.collaborationblog.mapper.UserMessageMapper;
import com.ytsssss.collaborationblog.service.UserMessageService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.UserMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ytsssss.collaborationblog.constant.status.GlobalResultStatus.MESSAGE_ADD_ERROR;

@Service
public class UserMessageServiceImpl implements UserMessageService{
    @Resource
    private UserMessageMapper userMessageMapper;
    @Autowired
    private UserService userService;

    @Override
    public int addMessage(Long userId, Long friendId, String content) throws Exception{
        UserMessage userMessage = new UserMessage();
        userMessage.setContent(content);
        userMessage.setUserId(userId);
        userMessage.setStatus(0);
        userMessage.setFriendId(friendId);
        userMessage.setCreateTime(new Date());
        userMessage.setUpdateTime(new Date());
        int code = userMessageMapper.insertSelective(userMessage);
        if (code != 1){
            throw new GlobalException(MESSAGE_ADD_ERROR);
        }
        return code;
    }

    @Override
    public int deleteMessage(Long contentId) {
        return userMessageMapper.deleteByPrimaryKey(contentId);
    }

    @Override
    public List<UserMessageVO> getUserMessage(Long friendId) {
        List<UserMessageVO> userMessageVOS = new ArrayList<>();
        List<UserMessage> userMessageList = userMessageMapper.getUserMessage(friendId);
        for (UserMessage userMessage : userMessageList){
            UserMessageVO userMessageVO = new UserMessageVO();
            userMessageVO.setCreateTime(TimeUtil.changeTimeToString(userMessage.getCreateTime()));
            userMessageVO.setUserId(userMessage.getUserId());
            userMessageVO.setId(userMessage.getId());
            userMessageVO.setContent(userMessage.getContent());
            userMessageVO.setFriendId(userMessage.getFriendId());
            User user = userService.getUserInfo(userMessage.getUserId());
            userMessageVO.setUserName(user.getName());
            userMessageVO.setAvatar(user.getAvatar());
            userMessageVO.setTextarea("");
            userMessageVO.setShowEdit(false);
            userMessageVOS.add(userMessageVO);
        }
        return userMessageVOS;
    }
}
