package com.ytsssss.collaborationblog.constant.statuscode;

import java.io.Serializable;

/**
 * Create by Ytsssss on 2018/1/22 10:15
 */
public class ResultStatus implements Serializable{
    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态信息
     */
    private final String msg;

    public ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    public ResultStatus format(Object... objects) {
        return new ResultStatus(this.code, String.format(msg, objects));
    }

    public ResultStatus append(Object... objects) {
        String newMsg = msg;
        for (Object item : objects) {
            newMsg = newMsg + item.toString();
        }
        return new ResultStatus(this.code, newMsg);
    }
}
