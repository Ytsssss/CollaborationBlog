package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.BlogMapper;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.vo.BlogVO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/23 19:58
 */
@Service
public class BlogServiceImpl implements BlogService{
    private static Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Resource
    private BlogMapper blogMapper;
    @Override
    public Object addBlog(BlogVO blogVO, User user) {
        Date date = new Date();
        Blog blog = new Blog();
        blog.setUserId(user.getId());
        blog.setContent(blogVO.getContent());
        blog.setIsComment(blogVO.getIsComment());
        blog.setIsPublic(blogVO.getIsPublic());
        blog.setStatus(0);
        blog.setReadTime(0L);
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        logger.info(blog.toString());

        return blogMapper.insert(blog);
    }

    @Override
    public Object editBlog(BlogVO blogVO, User user) {
        Date date = new Date();
        Blog blog = new Blog();
        blog.setId(blogVO.getId());
        blog.setContent(blogVO.getContent());
        blog.setIsComment(blogVO.getIsComment());
        blog.setIsPublic(blogVO.getIsPublic());
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


}
