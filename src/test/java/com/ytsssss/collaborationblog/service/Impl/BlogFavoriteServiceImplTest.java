package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.service.BlogFavoriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/25 15:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogFavoriteServiceImplTest {

    @Autowired
    BlogFavoriteService blogFavoriteService;
    @Test
    public void addBlogFavorite() {
        blogFavoriteService.addBlogFavorite(6L,9L);
    }

    @Test
    public void cancleBlogFavorite() {
        blogFavoriteService.cancelBlogFavorite(5L,9L);
    }
    @Test
    public void getBlogList(){
        blogFavoriteService.getBlogFavoriteList(9L);
    }
}