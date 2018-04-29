package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.vo.UserMessageVO;

import java.util.List;

public interface UserMessageService {
    /**
     * 添加消息
     * @param userId
     * @param friendId
     * @param content
     * @return
     * @throws Exception
     */
    int addMessage(Long userId, Long friendId, String content) throws Exception;

    /**
     * 删除消息
     * @param contentId
     * @return
     */
    int deleteMessage(Long contentId);

    /**
     * 获取消息列表
     * @param friendId
     * @return
     */
    List<UserMessageVO> getUserMessage(Long friendId);
}
