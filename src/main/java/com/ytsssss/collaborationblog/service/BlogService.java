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

    /**
     *  查看博客列表
     * @param userId
     * @param range 0:全部博客；1：我的博客；2：公开博客（不包括我的）；3：好友博客 ；4：草稿博客
     * @return
     */
    List<Long> getBlogList(Long userId, int range);

    /**
     * 通过blog id集合获取 blog集合
     * @param blogIdList
     * @return
     */
    List<Blog> getBlogList(List<Long> blogIdList);
}
