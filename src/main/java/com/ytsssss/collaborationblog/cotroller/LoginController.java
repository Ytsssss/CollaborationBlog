package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/19 15:14
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login/{accountId}/{password}")
    public Object login(@PathVariable("accountId") String accountId, @PathVariable("password") String password){
        Long isALLowLogin = userService.login(accountId, password);
        return isALLowLogin;
    }

}
