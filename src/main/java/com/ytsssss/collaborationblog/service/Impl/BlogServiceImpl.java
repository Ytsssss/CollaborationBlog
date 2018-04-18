package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.BlogFavoriteMapper;
import com.ytsssss.collaborationblog.mapper.BlogLikeMapper;
import com.ytsssss.collaborationblog.mapper.BlogMapper;
import com.ytsssss.collaborationblog.mapper.UserAttentionMapper;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import com.ytsssss.collaborationblog.service.BlogLikeService;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.BlogDetailVO;
import com.ytsssss.collaborationblog.vo.BlogVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.vo.HomeBlogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/23 19:58
 */
@Service
public class BlogServiceImpl implements BlogService{
    private static Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Resource
    private BlogMapper blogMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogLikeService blogLikeService;
    @Resource
    private BlogFavoriteMapper blogFavoriteMapper;
    @Resource
    private BlogLikeMapper blogLikeMapper;
    @Resource
    private UserAttentionMapper userAttentionMapper;

    @Override
    public Object addBlog(BlogVO blogVO, User user) {
        Date date = new Date();
        Blog blog = new Blog();
        blog.setUserId(user.getId());
        blog.setContent(blogVO.getContent());
        blog.setIsComment(blogVO.getIsComment());
        blog.setIsPublic(blogVO.getIsPublic());
        blog.setStatus(blogVO.getStatus());
        blog.setImg(blogVO.getImg());
        blog.setReadTime(0L);
        blog.setTitle(blogVO.getTitle());
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        logger.info(blog.toString());

        return blogMapper.insertSelective(blog);
    }

    @Override
    public Object editBlog(BlogVO blogVO, User user) {
        Date date = new Date();
        Blog blog = new Blog();
        blog.setContent(blogVO.getContent());
        blog.setIsComment(blogVO.getIsComment());
        blog.setIsPublic(blogVO.getIsPublic());
        blog.setTitle(blogVO.getTitle());
        blog.setUpdateTime(date);
        logger.info(blog.toString());

        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    public int deleteBlog(Long blogId) {
        return blogMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public List<Long> getBlogList(Long userId, int range) {
        List<Long> myBlogList = new ArrayList<>();
        List<Long> publicBlogList = new ArrayList<>();
        List<Long> friendBlogList = new ArrayList<>();
        List<Long> draftBlogList = new ArrayList<>();
        List<Long> allBlogList = new ArrayList<>();
        if (range == 1){
            logger.info("查询结果为："+blogMapper.getMyBlogList(userId));
            return blogMapper.getMyBlogList(userId);
        }else if (range == 2){
            logger.info("查询结果为："+blogMapper.getPublicBlobList(userId));
            return blogMapper.getPublicBlobList(userId);
        }else if (range == 3){
            logger.info("查询结果为："+blogMapper.getFriendBlobList(userId));
            return blogMapper.getFriendBlobList(userId);
        }else if (range == 4){
            logger.info("查询结果为："+blogMapper.getDraftBlogList(userId));
            return blogMapper.getDraftBlogList(userId);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Blog> getBlogList(List<Long> blogIdList) {
        logger.info("查询结果为："+blogMapper.getBlogListByIds(blogIdList));
        return blogMapper.getBlogListByIds(blogIdList);
    }

    @Override
    public List<HomeBlogVO> getHomeBlogList(List<Long> blogIdList){
        List<HomeBlogVO> homeBlogList = new ArrayList<HomeBlogVO>();
        List<Blog> blogList = getBlogList(blogIdList);
        for (Blog blog: blogList){
            User user = userService.getUserInfo(blog.getUserId());
            int commentCount = blogCommentService.getBlogCommentCount(blog.getId());
            int likeCount = blogLikeService.getBlogLikeCount(blog.getId());
            HomeBlogVO homeBlogVO = new HomeBlogVO();
            homeBlogVO.setId(blog.getId());
            homeBlogVO.setContent(blog.getContent());
            homeBlogVO.setCreateTime(TimeUtil.changeTimeToString(blog.getCreateTime()));
            homeBlogVO.setImg(blog.getImg());
            homeBlogVO.setTitle(blog.getTitle());
            homeBlogVO.setUpdateTime(TimeUtil.changeTimeToString(blog.getUpdateTime()));
            homeBlogVO.setUserId(blog.getUserId());
            homeBlogVO.setReadTime(blog.getReadTime());
            homeBlogVO.setCommentCount(commentCount);
            homeBlogVO.setUserName(user.getName());
            homeBlogVO.setUserAvatar(user.getAvatar());
            homeBlogVO.setLikeCount(likeCount);
            homeBlogList.add(homeBlogVO);
        }
        return homeBlogList;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        User user = userService.getUserInfo(blog.getUserId());
        boolean isLike = (blogLikeMapper.isLike(blogId, user.getId()) == 1);
        boolean isFollow = (userAttentionMapper.isAttention(user.getId(), blog.getUserId()) == 1);
        boolean isFavorite = (blogFavoriteMapper.isFavorite(blogId, user.getId()) == 1);
        BlogDetailVO blogDetailVO = new BlogDetailVO();
        blogDetailVO.setId(blogId);
        blogDetailVO.setContent(blog.getContent());
        blogDetailVO.setCreateTime(TimeUtil.changeTimeToString(blog.getCreateTime()));
        blogDetailVO.setReadTime(blog.getReadTime());
        blogDetailVO.setTitle(blog.getTitle());
        blogDetailVO.setUserId(blog.getUserId());
        blogDetailVO.setUserAvatar(user.getAvatar());
        blogDetailVO.setUserName(user.getName());
        blogDetailVO.setFollow(isFollow);
        blogDetailVO.setLike(isLike);
        blogDetailVO.setFavorite(isFavorite);
        blogDetailVO.setLikeCount(blogLikeService.getBlogLikeCount(blogId));
        return blogDetailVO;
    }
}
