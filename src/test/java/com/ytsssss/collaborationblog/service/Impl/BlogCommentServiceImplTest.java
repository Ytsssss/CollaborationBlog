package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.ytsssss.collaborationblog.domain.BlogCommentLike;
import com.ytsssss.collaborationblog.mapper.BlogCommentMapper;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/26 11:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCommentServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(BlogCommentServiceImplTest.class);
    @Autowired
    private BlogCommentService blogCommentService;
    @Resource
    private BlogCommentMapper blogCommentMapper;
    @Test
    public void addComment() {
        blogCommentService.addComment(5L, 6L,9L,6L,"写的asllld");
    }

    @Test
    public void deleteComment() {
        blogCommentService.deleteComment(4L);
    }

    @Test
    public void addCommentLike() {
        blogCommentService.addCommentLike(2L, 6L);
    }

    @Test
    public void cancelCommentLike() {
        blogCommentService.cancelCommentLike(2L, 6L);
    }
    @Test
    public void findComment(){
        logger.info(blogCommentMapper.getIdByReplyId(11L).toString());
        blogCommentMapper.getIdByReplyId(11L);
    }

    @Test
    public void getBlogCommentList(){
    }
}