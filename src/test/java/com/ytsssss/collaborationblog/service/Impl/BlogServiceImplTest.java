package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.vo.BlogVO;
import java.util.ArrayList;
import java.util.List;
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
        blogVO.setTitle("这是标题");
        blogVO.setPrecontent("hhhhh");
        blogVO.setContent("这是一篇博客");
        blogVO.setIsComment(0);
        blogVO.setIsPublic(0);
        User user = new User();
        user.setId(9L);
        blogService.addBlog(blogVO, user);
    }

    @Test
    public void editBlog(){
        BlogVO blogVO = new BlogVO();
        blogVO.setContent("这是一篇新博客");
        blogVO.setIsComment(0);
        blogVO.setIsPublic(0);
        blogVO.setStatus(0);
        User user = new User();
        user.setId(6L);
        blogService.editBlog(blogVO, user);
    }

    @Test
    public void deleteBlog(){
        blogService.deleteBlog(4L);
    }
    @Test
    public void getBlog(){
        blogService.getBlogList(6L,1);
    }

    @Test
    public void getList(){
        List<Long> list = new ArrayList<>();
        list.add(5L);
        list.add(6L);
        blogService.getBlogList(list);
    }
}