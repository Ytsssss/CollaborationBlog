package com.ytsssss.collaborationblog.vo;

public class FollowAttListVO {
    private Long userId;

    private String avatar;

    private String introduce;

    private String location;

    private String name;

    private String school;

    private boolean isFollow;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    @Override
    public String toString() {
        return "FollowAttListVO{" +
                "userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", introduce='" + introduce + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", isFollow=" + isFollow +
                '}';
    }
}
