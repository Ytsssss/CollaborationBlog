package com.ytsssss.collaborationblog.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ytsssss
 */
public class Blog implements Serializable {
    private Long id;

    private String content;

    private Date createTime;

    private Integer isComment;

    private String img;

    private Integer isPublic;

    private Long readTime;

    private Integer status;

    private Date updateTime;

    private Long userId;

    private String title;

    private String precontent;

    private static final long serialVersionUID = 1L;

    public String getPrecontent() {
        return precontent;
    }

    public void setPrecontent(String precontent) {
        this.precontent = precontent;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", isComment=" + isComment +
                ", img='" + img + '\'' +
                ", isPublic=" + isPublic +
                ", readTime=" + readTime +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", precontent='" + precontent + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Blog other = (Blog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsComment() == null ? other.getIsComment() == null : this.getIsComment().equals(other.getIsComment()))
            && (this.getIsPublic() == null ? other.getIsPublic() == null : this.getIsPublic().equals(other.getIsPublic()))
            && (this.getReadTime() == null ? other.getReadTime() == null : this.getReadTime().equals(other.getReadTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsComment() == null) ? 0 : getIsComment().hashCode());
        result = prime * result + ((getIsPublic() == null) ? 0 : getIsPublic().hashCode());
        result = prime * result + ((getReadTime() == null) ? 0 : getReadTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

}