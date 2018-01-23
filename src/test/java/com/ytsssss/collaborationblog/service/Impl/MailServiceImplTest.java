package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.constant.GlobalConstant;
import com.ytsssss.collaborationblog.service.MailService;
import com.ytsssss.collaborationblog.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Create by Ytsssss on 2018/1/22 17:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    MailService MailService;
    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("code", RandomUtil.getRandomNum(6));
        String emailContent = templateEngine.process("emailTemplate", context);
        MailService.sendTemplateMail("995170811@qq.com", GlobalConstant.MAILTITTLE,emailContent);
    }
}