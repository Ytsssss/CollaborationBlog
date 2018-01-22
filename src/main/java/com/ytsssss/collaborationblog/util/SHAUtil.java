package com.ytsssss.collaborationblog.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * SHA 加密算法
 * Create by Ytsssss on 2018/1/22 15:54
 */
public class SHAUtil {
    public static final String KEY_SHA = "SHA";

    public static  String  getResult(String inputStr)
    {
        BigInteger sha =null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }

}
