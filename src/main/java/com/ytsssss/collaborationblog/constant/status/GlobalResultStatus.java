package com.ytsssss.collaborationblog.constant.status;

/**
 * Create by Ytsssss on 2018/1/22 10:13
 */
public class GlobalResultStatus {

    public static final ResultStatus SUCCESS = new ResultStatus(0, "成功");
    public static final ResultStatus ERROR = new ResultStatus(10000, "失败");
    public static final ResultStatus ACCOUNTID_EXIST = new ResultStatus(10001,"该账号已注册");
    public static final ResultStatus PASSWORD_ERROR = new ResultStatus(10002, "密码错误");
    public static final ResultStatus ACCOUNTID_NOEXIST = new ResultStatus(10003, "该账号不存在");
    public static final ResultStatus MAILCODE_ERROR = new ResultStatus(10004,"邮箱验证码错误");
    public static final ResultStatus BLOG_ADD_ERROR = new ResultStatus(10005, "新增博客失败");
    public static final ResultStatus PARAM_ERROR = new ResultStatus(10006, "传入参数错误");
    public static final ResultStatus BLOG_EDIT_ERROR = new ResultStatus(10007,"编辑博客失败");
    public static final ResultStatus BLOG_DELETE_ERROR = new ResultStatus(10008,"删除博客失败");
    public static final ResultStatus BLOG_FAVORITE_ERROR = new ResultStatus(10009, "收藏博客失败");
    public static final ResultStatus BLOG_FAVORITE_CANCEL_ERROR = new ResultStatus(10009, "取消收藏失败");
    public static final ResultStatus BLOG_FAVORITE_LIST_ERROR = new ResultStatus(10009, "获取收藏列表失败");
    public static final ResultStatus BLOG_LIKE_ERROR = new ResultStatus(10009, "喜欢博客失败");
    public static final ResultStatus BLOG_LIKE_CANCEL_ERROR = new ResultStatus(10009, "取消喜欢失败");
    public static final ResultStatus BLOG_LIKE_LIST_ERROR = new ResultStatus(10009, "获取喜欢列表失败");
}
