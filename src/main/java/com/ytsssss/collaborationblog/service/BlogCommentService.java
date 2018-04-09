package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.BlogComment;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.vo.BlogCommentVO;

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
    List<BlogCommentVO> getBlogCommentList(Long blogId, User user);
}
