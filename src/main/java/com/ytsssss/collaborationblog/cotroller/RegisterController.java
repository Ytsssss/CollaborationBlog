package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.GlobalConstant;
import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.MailService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Create by Ytsssss on 2018/1/21 12:59
 */
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @PostMapping(value = "register")
    public Object register(@RequestParam("accountId") String accountId,
      @RequestParam("password")String password, @RequestParam("verifyCode") String code,
      @RequestParam("mailAddress") String mailAddress){
        Long userId = userService.register(accountId, password, code, mailAddress);
        if (userId.equals(-1L)){
            return JsonResult.fail(GlobalResultStatus.ACCOUNTID_EXIST);
        }else if (userId.equals(-2L)){
            return JsonResult.fail(GlobalResultStatus.MAILCODE_ERROR);
        }
        return JsonResult.success();
    }

    @GetMapping(value = "getMailCode")
    public Object getMailCode(@RequestParam("mailAddress") String mailAddress ){
        // 获取随机的验证码
        String code = RandomUtil.getRandomNum(6);
        // 将验证码存入redis 用于验证
        mailService.saveRedisForMailCode(code);
        String emailContent = getEmailContent(code);
        mailService.sendTemplateMail(mailAddress, GlobalConstant.MAILTITTLE, emailContent);
        return JsonResult.success();
    }

    private String getEmailContent(String code){
        Context context = new Context();
        context.setVariable("code", code);
        String emailContent = templateEngine.process("emailTemplate", context);
        return emailContent;
    }
}
