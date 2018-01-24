package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.vo.BlogVO;
import java.util.List;

/**
 * Create by Ytsssss on 2018/1/23 19:57
 */
public interface BlogService {

    /**
     *  新增博客
     * @param blogVO
     * @param user
     * @return
     */
    Object addBlog(BlogVO blogVO, User user);

    /**
     * 修改博客
     * @param blogVO
     * @param user
     * @return
     */
    Object editBlog(BlogVO blogVO, User user);

    /**
     * 删除博客
     * @param blogId
     * @return
     */
    int deleteBlog(Long blogId);


    List<Blog> getBlogList(Long userId);
}
