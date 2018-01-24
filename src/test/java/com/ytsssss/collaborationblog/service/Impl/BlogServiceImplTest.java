package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.vo.BlogVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/1/23 20:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceImplTest {

    @Autowired
    BlogService blogService;
    @Test
    public void addBlog() {
        BlogVO blogVO = new BlogVO();
        blogVO.setContent("这是一篇博客");
        blogVO.setIsComment(0);
        blogVO.setIsPublic(0);
        User user = new User();
        user.setId(6L);
        blogService.addBlog(blogVO, user);
    }
}