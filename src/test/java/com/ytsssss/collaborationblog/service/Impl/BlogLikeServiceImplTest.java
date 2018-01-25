package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.service.BlogLikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/25 18:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogLikeServiceImplTest {

    @Autowired
    BlogLikeService blogLikeService;
    @Test
    public void addBlogLike() {
        blogLikeService.addBlogLike(5L, 9L);
    }

    @Test
    public void cancelBlogLike() {
        blogLikeService.cancelBlogLike(6L,9L);
    }

    @Test
    public void getBlogLikeList() {
        blogLikeService.getBlogLikeList(9L);
    }
}