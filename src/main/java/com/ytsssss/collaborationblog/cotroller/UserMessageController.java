package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.UserMessageService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.vo.UserMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserMessageController {
    @Autowired
    private UserMessageService userMessageService;
    @Autowired
    private UserService userService;

    /**
     * 给好友发送消息
     * @param token
     * @param content
     * @param friendId
     * @return
     * @throws Exception
     */
    @PostMapping(value = "user/message/add")
    public Object addMessage(@RequestParam("token") String token,
                             @RequestParam("content") String content,
                             @RequestParam("friendId") Long friendId) throws Exception{
        User user = userService.getUserByToken(token);
        userMessageService.addMessage(user.getId(), friendId, content);
        return JsonResult.success();
    }

    /**
     * 删除好友消息
     * @param id
     * @return
     */
    @PostMapping(value = "user/message/delete")
    public Object delMessage(@RequestParam("id") Long id){
        userMessageService.deleteMessage(id);
        return JsonResult.success();
    }

    /**
     * 获取消息列表
     * @param token
     * @return
     */
    @GetMapping(value = "user/message/getList/{token}")
    public Object getList(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<UserMessageVO> userMessageVOList = userMessageService.getUserMessage(user.getId());
        return JsonResult.success(userMessageVOList);
    }
}
