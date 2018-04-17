package com.ytsssss.collaborationblog.handler;

import com.ytsssss.collaborationblog.exception.GlobalException;
import com.ytsssss.collaborationblog.json.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e){
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e;
            return JsonResult.fail(globalException.getCode(), globalException.getMessage());
        }else {
            logger.error("【系统异常】{}"+e);
            return JsonResult.fail(10000, "未知错误");
        }
    }
}
