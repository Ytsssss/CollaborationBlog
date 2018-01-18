package com.ytsssss.collaborationblog.entity;

import java.util.Date;

/**
 * Create by Ytsssss on 2018/1/18
 */
public class BlogLike {
    //博客点赞id，自增
    private Long id;
    //博客id
    private Long blogId;
    //用户id
    private Long userId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
