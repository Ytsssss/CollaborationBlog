package com.ytsssss.collaborationblog.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Create by Ytsssss on 2018/1/18
 */
@Entity
public class Team {
    @Id
    @GeneratedValue
    //团队id，自增
    private Long id;
    //团队名
    private String name;
    //负责用户id
    private Long userId;
    //团队描述
    private String describe;
    //团队状态; 0:正常，1：删除
    private Integer status;
    //是否公开; 0:公开， 1：不公开
    private Integer isPublic;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
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
