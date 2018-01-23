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
}
