package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.BlogComment;
import com.ytsssss.collaborationblog.domain.BlogCommentLike;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.mapper.BlogCommentLikeMapper;
import com.ytsssss.collaborationblog.mapper.BlogCommentMapper;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.BlogCommentVO;
import com.ytsssss.collaborationblog.vo.CommentMessVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @Override
    public int addComment(Long blogId, Long userId, Long replyCommentId, Long replyUserId,
      String content) {
        BlogComment blogComment = new BlogComment();
        blogComment.setBlogId(blogId);
        blogComment.setUserId(userId);
        blogComment.setContent(content);
        blogComment.setStatus(0);
        if (replyCommentId != 0 || !replyCommentId.equals(0)){
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

    @Override
    public int getBlogCommentCount(Long blogId) {
        return blogCommentMapper.getBlogCommentCount(blogId);
    }

    @Override
    public List<BlogCommentVO> getBlogCommentList(Long blogId, User currrentUser) {
        List<BlogComment> blogComments = blogCommentMapper.getBlogCommentList(blogId);
        logger.info(blogComments.toString());
        List<BlogCommentVO> blogCommentVOList = new ArrayList<>();
        for (BlogComment blogComment : blogComments){
            BlogCommentVO blogCommentVO = new BlogCommentVO();
            User user = userService.getUserInfo(blogComment.getUserId());
            if (blogComment.getReplyUserId() != 0){
                User userReply = userService.getUserInfo(blogComment.getUserId());
                blogCommentVO.setReplyUserName(userReply.getName());
                blogCommentVO.setRelyUserAvatar(userReply.getAvatar());
                blogCommentVO.setHasReply(true);
            }else {
                blogCommentVO.setHasReply(false);
            }
            //判断当前用户是否点赞该评论
            boolean isLike = (blogCommentLikeMapper.isLikeComment(blogComment.getId(), currrentUser.getId()) == 1);
            int likeCount = blogCommentLikeMapper.getCommentCount(blogComment.getId());
            blogCommentVO.setId(blogComment.getId());
            blogCommentVO.setCreateTime(TimeUtil.changeTimeToString(blogComment.getCreateTime()));
            blogCommentVO.setLikeCount(likeCount);
            blogCommentVO.setLike(isLike);
            blogCommentVO.setComment(blogComment.getContent());
            blogCommentVO.setUserId(blogComment.getUserId());
            blogCommentVO.setUserAvatar(user.getAvatar());
            blogCommentVO.setUserName(user.getName());
            blogCommentVO.setReplyUserId(blogComment.getReplyUserId());
            blogCommentVO.setShowEdit(false);
            blogCommentVO.setTextarea("");
            blogCommentVOList.add(blogCommentVO);
        }
        return blogCommentVOList;
    }

    @Override
    public List<CommentMessVO> getMessComment(Long userId) {
        List<Long> blogList = blogService.getBlogList(userId, 1);
        List<BlogComment> blogCommentList = blogCommentMapper.getBeCommentList(blogList);
        List<CommentMessVO> commentMessVOList = new ArrayList<>();
        for (BlogComment blogComment : blogCommentList){
            User user = userService.getUserInfo(blogComment.getUserId());
            Blog blog = blogService.getBlogInfo(blogComment.getBlogId());
            CommentMessVO comment = new CommentMessVO();
            comment.setBlogId(blogComment.getBlogId());
            comment.setCreateTime(TimeUtil.changeTimeToString(blogComment.getCreateTime()));
            comment.setAction("评论");
            comment.setContent(blogComment.getContent());
            comment.setId(blogComment.getId());
            comment.setUserId(blogComment.getUserId());
            comment.setUserName(user.getName());
            comment.setAvatar(user.getAvatar());
            comment.setTitle(blog.getTitle());
            commentMessVOList.add(comment);
        }
        return commentMessVOList;
    }
}
