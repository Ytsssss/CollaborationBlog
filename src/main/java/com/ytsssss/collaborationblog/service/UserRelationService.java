package com.ytsssss.collaborationblog.service;

import java.util.List;

/**
 * Create by Ytsssss on 2018/1/31 14:26
 */
public interface UserRelationService {

    /**
     * 关注
     * @param userId
     * @param attentionId
     * @return
     */
    int addUserAttention(Long userId, Long attentionId);

    /**
     * 取消关注
     * @param userId
     * @param attentionId
     * @return
     */
    int cancelUserAttention(Long userId, Long attentionId);

    /**
     * 获取粉丝列表
     * @param userId
     * @return
     */
    List<Long> getFansList(Long userId);

    /**
     * 获取关注列表
     * @param userId
     * @return
     */
    List<Long> getAttentionList(Long userId);

    /**
     * 请求添加好友
     * @param userId
     * @param friendId
     * @return
     */
    int addUserFriend(Long userId, Long friendId);

    /**
     * 删除好友
     * @param userId
     * @param friendId
     * @return
     */
    int deleteUserFriend(Long userId, Long friendId);

    /**
     * 确认好友关系
     * @param userId
     * @param friendId
     * @param status
     * @return
     */
    int confirmUserFriend(Long userId, Long friendId, int status);

    /**
     * 获取好友列表
     * @param userId
     * @return
     */
    List<Long> getUserFriendList(Long userId);

    /**
     * 获取请求添加好友列表
     * @param userId
     * @return
     */
    List<Long> getQuestFriendList(Long userId);
}
