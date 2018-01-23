package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/21 12:59
 */
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    @PostMapping(value = "register")
    public Object register(@RequestParam("accountId") String accountId, @RequestParam("password")String password){
        Long userId = userService.register(accountId, password);
        if (userId.equals(-1L)){
            return JsonResult.fail(GlobalResultStatus.ACCOUNTID_EXIST);
        }
        return JsonResult.success();
    }
}
