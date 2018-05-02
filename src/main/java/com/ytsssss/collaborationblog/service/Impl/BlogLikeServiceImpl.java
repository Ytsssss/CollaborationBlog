package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.BlogLike;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.BlogLikeMapper;
import com.ytsssss.collaborationblog.service.BlogLikeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.BlogLikeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/25 14:59
 */
@Service
public class BlogLikeServiceImpl implements BlogLikeService{
    private static Logger logger = LoggerFactory.getLogger(BlogLikeServiceImpl.class);
    @Resource
    private BlogLikeMapper blogLikeMapper;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @Override
    public int getBlogLikeCount(Long blogId) {
        return blogLikeMapper.getBlogLikeCount(blogId);
    }

    @Override
    public List<BlogLikeVO> getBeLikeList(Long userId) {
        List<Long> blogList = blogService.getBlogList(userId, 1);
        List<BlogLikeVO> blogLikeVOList = new ArrayList<>();
        if (blogList.size() != 0){
            List<BlogLike> blogLikeList = blogLikeMapper.getBeLikeList(blogList);
            for (BlogLike blogLike : blogLikeList){
                User user = userService.getUserInfo(blogLike.getUserId());
                Blog blog = blogService.getBlogInfo(blogLike.getBlogId());
                BlogLikeVO blogLikeVO = new BlogLikeVO();
                blogLikeVO.setBlogId(blogLike.getBlogId());
                blogLikeVO.setCreateTime(TimeUtil.changeTimeToString(blogLike.getCreateTime()));
                blogLikeVO.setAction("点赞");
                blogLikeVO.setId(blogLike.getId());
                blogLikeVO.setUserId(blogLike.getUserId());
                blogLikeVO.setUserName(user.getName());
                blogLikeVO.setAvatar(user.getAvatar());
                blogLikeVO.setTitle(blog.getTitle());
                blogLikeVOList.add(blogLikeVO);
            }
        }else {
            blogLikeVOList = Collections.emptyList();
        }
        return blogLikeVOList;
    }

    @Override
    public int addBlogLike(Long blogId, Long userId) {
        BlogLike blogLike = new BlogLike();
        Date date = new Date();
        blogLike.setBlogId(blogId);
        blogLike.setUserId(userId);
        blogLike.setCreateTime(date);
        blogLike.setUpdateTime(date);
        logger.info(blogLike.toString());

        return blogLikeMapper.insertSelective(blogLike);
    }

    @Override
    public int cancelBlogLike(Long blogId, Long userId) {
        return blogLikeMapper.deleteByBlogAndUserId(blogId, userId);
    }

    @Override
    public List<Long> getBlogLikeList(Long userId) {
        logger.info("查询的结果为："+blogLikeMapper.getBlogListByUserId(userId));

        return blogLikeMapper.getBlogListByUserId(userId);
    }
}
