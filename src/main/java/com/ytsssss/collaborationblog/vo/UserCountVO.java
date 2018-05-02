package com.ytsssss.collaborationblog.vo;

public class UserCountVO {
    private int blogCount;

    private int likeCount;

    private int followCount;

    private int fansCount;

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    @Override
    public String toString() {
        return "UserCountVO{" +
                "blogCount=" + blogCount +
                ", likeCount=" + likeCount +
                ", followCount=" + followCount +
                ", fansCount=" + fansCount +
                '}';
    }
}
