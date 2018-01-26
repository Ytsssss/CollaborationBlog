package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.domain.BlogCommentLike;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/26 11:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCommentServiceImplTest {
    @Autowired
    private BlogCommentService blogCommentService;
    @Test
    public void addComment() {
        blogCommentService.addComment(5L, 6L,7L,6L,"写的asd");
    }

    @Test
    public void deleteComment() {
        blogCommentService.deleteComment(1L);
    }

    @Test
    public void addCommentLike() {
        blogCommentService.addCommentLike(2L, 6L);
    }

    @Test
    public void cancelCommentLike() {
        blogCommentService.cancelCommentLike(2L, 6L);
    }
}