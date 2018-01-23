package com.ytsssss.collaborationblog.service;

/**
 * Create by Ytsssss on 2018/1/22 16:59
 */
public interface MailService {
    void sendTemplateMail(String to, String subject, String content);
    void saveRedisForMailCode(String code);
}
