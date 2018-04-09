package com.ytsssss.collaborationblog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将timestamp转换为string
 */
public class TimeUtil {

    public static String changeTimeToString(Date timeStamp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转成后的时间的格式
        String time = sdf.format(timeStamp);   // 时间戳转换成时间
        return time;
    }
}
