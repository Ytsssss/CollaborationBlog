package com.ytsssss.collaborationblog.json;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.constant.status.ResultStatus;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Create by Ytsssss on 2018/1/22 10:06
 */
public class JsonResult {

    /**
     * 只返回成功 code
     * @return
     */
    public static Object success(){
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("code", GlobalResultStatus.SUCCESS.getCode());
        map.put("data","");
        return map;
    }

    /**
     * 返回传入的对象数据
     * @param data
     * @return
     */
    public static Object success(Object data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", GlobalResultStatus.SUCCESS.getCode());
        map.put("data", data);
        return map;
    }

    /**
     * 返回传入的 MAP 数据
     * @param data
     * @return
     */
    public static Object success(Map<String, Object> data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", GlobalResultStatus.SUCCESS.getCode());
        map.put("data", data);
        return map;
    }

    /**
     * 返回失败 code 和 message
     * @param status
     * @return
     */
    public static Object fail(ResultStatus status){
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("code", status.getCode());
        map.put("data",status.getMsg());
        return map;
    }
    public static Object fail(Object object){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", GlobalResultStatus.ERROR.getCode());
        map.put("data", object);
        return map;
    }
    public static Object fail(Integer code, Object object){
        Map<String, Object> map= new HashMap<String, Object>();
        map.put("code", code);
        map.put("data",object);
        return map;
    }
}
