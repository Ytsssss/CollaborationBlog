package com.ytsssss.collaborationblog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String changeTimeToDay(Date timeStamp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转成后的时间的格式
        String time = sdf.format(timeStamp);   // 时间戳转换成时间
        return time;
    }

    public static String  getCurDate(int offset){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();

        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -offset);  //设置为前n天
        dBefore = calendar.getTime();   //得到前n天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转成后的时间的格式
        String time = sdf.format(dBefore);   // 时间戳转换成时间
        return time;
    }
}
