package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.service.MailService;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Create by Ytsssss on 2018/1/22 16:59
 */
@Component
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;

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
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
