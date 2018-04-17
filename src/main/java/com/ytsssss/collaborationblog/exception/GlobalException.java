package com.ytsssss.collaborationblog.exception;
import com.ytsssss.collaborationblog.constant.status.ResultStatus;

/**
 * Create by Ytsssss on 2018/1/23 9:55
 */
public class GlobalException extends Exception{
    //继承自 RuntimeException而不继承 exception 是由于 spring框架对于抛出RuntimeException异常才进行事务回滚
    private Integer code ;

    public GlobalException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
        this.code = resultStatus.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
