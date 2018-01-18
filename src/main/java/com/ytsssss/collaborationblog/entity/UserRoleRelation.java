package com.ytsssss.collaborationblog.entity;

import java.util.Date;

/**
 * Create by Ytsssss on 2018/1/18
 */
public class UserRoleRelation {
    //用户查看权限id，自增
    private Long id;
    //博客id
    private Long blogId;
    //用户id
    private Long userId;
    //用户角色; 0: 查看，1：编辑，2：删除，3：全部权限
    private Integer userRole;
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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
