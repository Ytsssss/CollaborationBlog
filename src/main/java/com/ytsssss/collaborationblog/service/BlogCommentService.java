package com.ytsssss.collaborationblog.service;

/**
 * Create by Ytsssss on 2018/1/25 19:49
 */
public interface BlogCommentService {
    int addComment(Long blogId, Long userId, Long replyCommentId, Long replyUserId, String content);
    int deleteComment(Long commentId);
    int addCommentLike(Long blogCommentId, Long userId);
    int cancelCommentLike(Long blogCommentId, Long userId);
}
