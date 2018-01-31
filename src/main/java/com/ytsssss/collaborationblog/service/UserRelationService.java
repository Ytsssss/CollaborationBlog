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
     * 添加好友
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
}
