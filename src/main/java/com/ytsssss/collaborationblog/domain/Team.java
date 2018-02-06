package com.ytsssss.collaborationblog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 
 */
public class Team implements Serializable {
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private Long userId;

    private Integer isPublic;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Team{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", description='" + description + '\'' +
          ", status=" + status +
          ", userId=" + userId +
          ", isPublic=" + isPublic +
          ", createTime=" + createTime +
          ", updateTime=" + updateTime +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
          Objects.equals(name, team.name) &&
          Objects.equals(description, team.description) &&
          Objects.equals(status, team.status) &&
          Objects.equals(userId, team.userId) &&
          Objects.equals(isPublic, team.isPublic) &&
          Objects.equals(createTime, team.createTime) &&
          Objects.equals(updateTime, team.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects
          .hash(id, name, description, status, userId, isPublic, createTime, updateTime);
    }
}