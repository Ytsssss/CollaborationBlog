package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/19 14:43
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getuserinfo/{id}")
    public Object getUserInfo(@PathVariable("id") Long userId){
        User user = userService.getUserInfo(userId);
        return JsonResult.success(user);
    }

    @GetMapping(value = "user/info/{token}")
    public Object userInfo(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        return JsonResult.success(user);
    }
}
