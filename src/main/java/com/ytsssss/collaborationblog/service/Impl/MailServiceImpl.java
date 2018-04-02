package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.constant.GlobalConstant;
import com.ytsssss.collaborationblog.service.MailService;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Create by Ytsssss on 2018/1/22 16:59
 */
@Component
public class MailServiceImpl implements MailService{
    private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendTemplateMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            mailSender.send(message);
            logger.info("发送邮件到:"+to);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void saveRedisForMailCode(String code) {
        try {
            ValueOperations<String, String> operations=redisTemplate.opsForValue();
            operations.set(GlobalConstant.MAILCODE,code);
            logger.info("验证码为: "+code);
        }catch (Exception e){
            logger.error("将邮件验证码存入redis出现错误 "+e.getMessage());
        }
    }

}
