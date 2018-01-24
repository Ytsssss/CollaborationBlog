package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.vo.BlogVO;

/**
 * Create by Ytsssss on 2018/1/23 19:57
 */
public interface BlogService {
    Object addBlog(BlogVO blogVO, User user);
}
