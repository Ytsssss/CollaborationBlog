package com.ytsssss.collaborationblog.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Create by Ytsssss on 2018/1/18
 */
@Entity
public class UserFriend {
    @Id
    @GeneratedValue
    //用户好友表id，自增
    private Long id;
    //好友id
    private Long friendId;
    //用户id
    private Long userId;
    //状态； 0：正常， 1：删除
    private Integer status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public UserFriend() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
