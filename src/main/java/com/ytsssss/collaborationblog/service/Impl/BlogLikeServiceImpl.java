package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.BlogFavorite;
import com.ytsssss.collaborationblog.domain.BlogLike;
import com.ytsssss.collaborationblog.mapper.BlogFavoriteMapper;
import com.ytsssss.collaborationblog.mapper.BlogLikeMapper;
import com.ytsssss.collaborationblog.service.BlogFavoriteService;
import com.ytsssss.collaborationblog.service.BlogLikeService;
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
public class BlogLikeServiceImpl implements BlogLikeService{
    private static Logger logger = LoggerFactory.getLogger(BlogLikeServiceImpl.class);
    @Resource
    private BlogLikeMapper blogLikeMapper;
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
