package com.ytsssss.collaborationblog.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Create by Ytsssss on 2018/1/18
 */
@Entity
public class BlogLike {
    @Id
    @GeneratedValue
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

    public BlogLike() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
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
