package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.BlogFavorite;
import com.ytsssss.collaborationblog.mapper.BlogFavoriteMapper;
import com.ytsssss.collaborationblog.service.BlogFavoriteService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/25 14:59
 */
@Service
public class BlogFavoriteServiceImpl implements BlogFavoriteService{
    private static Logger logger = LoggerFactory.getLogger(BlogFavoriteServiceImpl.class);
    @Resource
    private BlogFavoriteMapper blogFavoriteMapper;
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
}
