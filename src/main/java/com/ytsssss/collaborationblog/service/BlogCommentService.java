package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.BlogComment;
import java.util.List;

/**
 * Create by Ytsssss on 2018/1/25 19:49
 */
public interface BlogCommentService {
    int addComment(Long blogId, Long userId, Long replyCommentId, Long replyUserId, String content);
    int deleteComment(Long commentId);
    int addCommentLike(Long blogCommentId, Long userId);
    int cancelCommentLike(Long blogCommentId, Long userId);
    int getBlogCommentCount(Long blogId);
    List<BlogComment> getBlogCommentList(Long blogId);
}
