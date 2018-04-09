package com.ytsssss.collaborationblog.vo;

import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ytsssss
 */
public class BlogVO {
    @NotBlank(message = "博客内容必传")
    private String content;

    private Integer isComment;

    private Integer isPublic;

    private Integer status;

    private String img;

    private static final long serialVersionUID = 1L;

    public String getImg() {
        return img;
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