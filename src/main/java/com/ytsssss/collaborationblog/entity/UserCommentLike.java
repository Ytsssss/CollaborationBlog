package com.ytsssss.collaborationblog.entity;

import java.util.Date;

/**
 * Create by Ytsssss on 2018/1/18
 */
public class UserCommentLike {
    //博客评论点赞id，自增
    private Long id;
    //博客id
    private Long blogCommentId;
    //用户id
    private Long userId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogCommentId() {
        return blogCommentId;
    }

    public void setBlogCommentId(Long blogCommentId) {
        this.blogCommentId = blogCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
