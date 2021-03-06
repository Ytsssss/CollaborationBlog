package com.ytsssss.collaborationblog.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.xml.soap.Text;
import javax.persistence.Id;

/**
 * Create by Ytsssss on 2018/1/18
 */
@Entity
public class Blog {
    @Id
    @GeneratedValue
    //博客id，自增
    private Long id;
    //用户id
    private Long userId;
    //状态；0：正常，1：草稿，2：删除
    private Integer status;
    //是否允许评论; 0：允许，1：不允许
    private Integer isComment;
    //阅读数
    private Long readTime;
    //是否公开; 0：全部可见，1：部分可见，2：私密
    private Integer isPublic;
    //博客内容
    private String content;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
