package com.ytsssss.collaborationblog.exception;

import com.ytsssss.collaborationblog.constant.GlobalConstant;
import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.constant.status.ResultStatus;
import com.ytsssss.collaborationblog.json.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by Ytsssss on 2018/1/23 9:55
 */
@ControllerAdvice
public class GlobalException extends Exception{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Exception e){
        return null;
    }
}
