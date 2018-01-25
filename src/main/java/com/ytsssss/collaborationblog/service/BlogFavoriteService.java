package com.ytsssss.collaborationblog.service;

import java.util.List;

/**
 * Create by Ytsssss on 2018/1/25 14:58
 */
public interface BlogFavoriteService {

    /**
     * 收藏博客
     * @param blogId
     * @param userId
     * @return
     */
    int addBlogFavorite(Long blogId, Long userId);

    /**
     * 取消收藏博客
     * @param blogId
     * @param userId
     * @return
     */
    int cancelBlogFavorite(Long blogId, Long userId);

    /**
     * 获取收藏列表
     * @param userId
     * @return
     */
    List<Long> getBlogFavoriteList(Long userId);
}
