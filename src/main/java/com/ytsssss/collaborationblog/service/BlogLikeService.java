package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.vo.BlogLikeVO;

import java.util.List;

/**
 * Create by Ytsssss on 2018/1/25 14:58
 */
public interface BlogLikeService {

    /**
     * 喜欢博客
     * @param blogId
     * @param userId
     * @return
     */
    int addBlogLike(Long blogId, Long userId);

    /**
     * 取消喜欢博客
     * @param blogId
     * @param userId
     * @return
     */
    int cancelBlogLike(Long blogId, Long userId);

    /**
     * 获取喜欢列表
     * @param userId
     * @return
     */
    List<Long> getBlogLikeList(Long userId);

    /**
     * 获取博客点赞数
     * @param blogId
     * @return
     */
    int getBlogLikeCount(Long blogId);

    /**
     * 获取自己被赞的文章
     * @param userId
     * @return
     */
    List<BlogLikeVO> getBeLikeList(Long userId);
}
