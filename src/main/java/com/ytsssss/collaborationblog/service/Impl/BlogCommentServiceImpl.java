package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.BlogComment;
import com.ytsssss.collaborationblog.domain.BlogCommentLike;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.mapper.BlogCommentLikeMapper;
import com.ytsssss.collaborationblog.mapper.BlogCommentMapper;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/25 19:50
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService{
    private static Logger logger = LoggerFactory.getLogger(BlogCommentServiceImpl.class);
    @Resource
    private BlogCommentMapper blogCommentMapper;
    @Resource
    private BlogCommentLikeMapper blogCommentLikeMapper;

    @Override
    public int addComment(Long blogId, Long userId, Long replyCommentId, Long replyUserId,
      String content) {
        BlogComment blogComment = new BlogComment();
        blogComment.setBlogId(blogId);
        blogComment.setUserId(userId);
        blogComment.setContent(content);
        blogComment.setStatus(0);
        if (replyCommentId != null || !"".equals(replyCommentId)){
            blogComment.setReplyCommentId(replyCommentId);
            blogComment.setReplyUserId(replyUserId);
        }
        logger.info(blogComment.toString());

        return blogCommentMapper.insertSelective(blogComment);
    }

    @Override
    public int deleteComment(Long commentId) {
        return blogCommentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public int addCommentLike(Long blogCommentId, Long userId) {
        BlogCommentLike blogCommentLike = new BlogCommentLike();
        blogCommentLike.setBlogCommentId(blogCommentId);
        blogCommentLike.setUserId(userId);
        return blogCommentLikeMapper.insertSelective(blogCommentLike);
    }

    @Override
    public int cancelCommentLike(Long blogCommentId, Long userId) {
        return blogCommentLikeMapper.deleteByBlogCommentAndUserId(blogCommentId,userId);
    }
}
