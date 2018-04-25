package com.ytsssss.collaborationblog.vo;

import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ytsssss
 */
public class BlogVO {
    private Long id;
    @NotBlank(message = "博客内容不能为空")
    private String content;

    private Integer isComment;

    private Integer isPublic;

    private Integer status;

    private String img;

    private String precontent;
    @NotBlank(message = "博客标题不能为空")
    private String title;

    private static final long serialVersionUID = 1L;

    public String getPrecontent() {
        return precontent;
    }

    public void setPrecontent(String precontent) {
        this.precontent = precontent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}