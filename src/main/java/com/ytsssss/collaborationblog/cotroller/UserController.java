package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.UserRelationService;
import com.ytsssss.collaborationblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Ytsssss on 2018/1/19 14:43
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRelationService userRelationService;

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

    @PostMapping(value = "user/attention")
    public Object attention(@RequestParam("token") String token,
                            @RequestParam("attentionId") Long attentionId){
        User user = userService.getUserByToken(token);
        int code = userRelationService.addUserAttention(user.getId(), attentionId);
        if (code < 1){
            return JsonResult.fail(GlobalResultStatus.ERROR);
        }
        return JsonResult.success();
    }

    @PostMapping(value = "user/cancelAttention")
    public Object cancelAttention(@RequestParam("token") String token,
                                  @RequestParam("attentionId") Long attentionId){
        User user = userService.getUserByToken(token);
        int code = userRelationService.cancelUserAttention(user.getId(), attentionId);
        if (code < 1){
            return JsonResult.fail(GlobalResultStatus.ERROR);
        }
        return JsonResult.success();
    }
}
