package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.BlogFavorite;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.BlogFavoriteMapper;
import com.ytsssss.collaborationblog.service.BlogFavoriteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.BlogFavoriteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/25 14:59
 */
@Service
public class BlogFavoriteServiceImpl implements BlogFavoriteService{
    private static Logger logger = LoggerFactory.getLogger(BlogFavoriteServiceImpl.class);
    @Resource
    private BlogFavoriteMapper blogFavoriteMapper;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @Override
    public int addBlogFavorite(Long blogId, Long userId) {
        BlogFavorite blogFavorite = new BlogFavorite();
        Date date = new Date();
        blogFavorite.setBlogId(blogId);
        blogFavorite.setUserId(userId);
        blogFavorite.setCreateTime(date);
        blogFavorite.setUpdateTime(date);
        logger.info(blogFavorite.toString());

        return blogFavoriteMapper.insertSelective(blogFavorite);
    }

    @Override
    public int cancelBlogFavorite(Long blogId, Long userId) {
        return blogFavoriteMapper.deleteByBlogAndUserId(blogId, userId);
    }

    @Override
    public List<Long> getBlogFavoriteList(Long userId) {
        logger.info("查询的结果为："+blogFavoriteMapper.getBlogListByUserId(userId));

        return blogFavoriteMapper.getBlogListByUserId(userId);
    }

    @Override
    public List<BlogFavoriteVO> getBeFavoList(Long userId) {
        List<Long> blogList = blogService.getBlogList(userId, 1);
        List<BlogFavorite> blogFavoriteList = blogFavoriteMapper.getBeFavoList(blogList);
        List<BlogFavoriteVO> blogFavoriteVOList = new ArrayList<>();
        for (BlogFavorite blogLike : blogFavoriteList){
            User user = userService.getUserInfo(blogLike.getUserId());
            Blog blog = blogService.getBlogInfo(blogLike.getBlogId());
            BlogFavoriteVO blogFavoriteVO = new BlogFavoriteVO();
            blogFavoriteVO.setBlogId(blogLike.getBlogId());
            blogFavoriteVO.setCreateTime(TimeUtil.changeTimeToString(blogLike.getCreateTime()));
            blogFavoriteVO.setAction("收藏");
            blogFavoriteVO.setId(blogLike.getId());
            blogFavoriteVO.setUserId(blogLike.getUserId());
            blogFavoriteVO.setUserName(user.getName());
            blogFavoriteVO.setAvatar(user.getAvatar());
            blogFavoriteVO.setTitle(blog.getTitle());
            blogFavoriteVOList.add(blogFavoriteVO);
        }
        return blogFavoriteVOList;
    }
}
