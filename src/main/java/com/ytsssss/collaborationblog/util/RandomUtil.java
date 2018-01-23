package com.ytsssss.collaborationblog.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生成随机数
 * Create by Ytsssss on 2018/1/22 19:39
 */
public class RandomUtil {

    /**
     *  取得有字符和数字的组合验证码
     * @param length
     * @return
     */
    public static String getRandom(int length){
        String strChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        strChar+=strChar.toLowerCase();
        String strNum = "0123456789";
        strChar+=strNum;
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i=0;i<length;i++){
            stringBuilder.append(strChar.charAt(new Random().nextInt(strChar.length())));
        }
        return stringBuilder.toString();
    }

    /**
     *  获取随机的几个字符
     * @param length
     * @return
     */
    public static String getRandomNum(int length){
        return UUID.randomUUID().toString().substring(0,length);
    }
}
