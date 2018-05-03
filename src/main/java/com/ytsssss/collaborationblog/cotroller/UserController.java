package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.domain.UserFriend;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.UserRelationService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.vo.FollowAttListVO;
import com.ytsssss.collaborationblog.vo.UserChangeVO;
import com.ytsssss.collaborationblog.vo.UserFriendVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import javax.validation.Valid;
import java.util.List;

/**
 * Create by Ytsssss on 2018/1/19 14:43
 */
@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
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

    /**
     * 修改用户信息
     * @param token
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "user/changeInfo")
    public Object changeUserInfo(@RequestParam("token") String token,
                                 @Valid UserChangeVO user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getFieldError().getDefaultMessage());
            return JsonResult.fail(GlobalResultStatus.PARAM_ERROR);
        }
        userService.changeUserInfo(user,token);
        return JsonResult.success();
    }

    /**
     * 获取粉丝列表
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping(value = "user/getFollowList/{token}")
    public Object getFollowList(@PathVariable("token") String token)throws Exception{
        User user = userService.getUserByToken(token);
        List<FollowAttListVO> followAttListVOList = userRelationService.getFansList(user.getId());
        return JsonResult.success(followAttListVOList);
    }

    /**
     * 获取关注列表
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping(value = "user/getAttentionList/{token}")
    public Object getAttentionList(@PathVariable("token") String token)throws Exception{
        User user = userService.getUserByToken(token);
        List<FollowAttListVO> followAttListVOList = userRelationService.getAttentionList(user.getId());
        return JsonResult.success(followAttListVOList);
    }

    /**
     * 获取相互关注的列表
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping(value = "user/getEachList/{token}")
    public Object getEachList(@PathVariable("token") String token)throws Exception{
        User user = userService.getUserByToken(token);
        List<FollowAttListVO> followAttListVOList = userRelationService.getEachList(user.getId());
        return JsonResult.success(followAttListVOList);
    }

    /**
     * 添加朋友
     * @param token
     * @param userId
     * @return
     */
    @PostMapping(value = "friend/add")
    public Object addFriend(@RequestParam("token") String token, @RequestParam("userId") Long userId){
        User user = userService.getUserByToken(token);
        userRelationService.addUserFriend(user.getId(), userId);
        return JsonResult.success();
    }

    /**
     * 删除朋友（拒绝加为好友）
     * @param token
     * @param userId
     * @return
     */
    @PostMapping(value = "friend/delete")
    public Object deleteFriend(@RequestParam("token") String token, @RequestParam("userId") Long userId){
        User user = userService.getUserByToken(token);
        userRelationService.deleteUserFriend(userId, user.getId());
        return JsonResult.success();
    }

    /**
     * 删除好友
     * @param token
     * @param userId
     * @return
     */
    @PostMapping(value = "friend/del")
    public Object delFriend(@RequestParam("token") String token, @RequestParam("userId") Long userId){
        User user = userService.getUserByToken(token);
        userRelationService.deleteUserFriend(user.getId(), userId);
        return JsonResult.success();
    }

    /**
     * 确定好友关系（status = 0表示确定加好友，status = 2表示拒绝）
     * @param token
     * @param userId
     * @param status
     * @return
     */
    @PostMapping(value = "friend/confirm")
    public Object confirmFriend(@RequestParam("token") String token, @RequestParam("userId") Long userId, @RequestParam("status") int status){
        User user = userService.getUserByToken(token);
        userRelationService.confirmUserFriend(userId, user.getId(), status);
        return JsonResult.success();
    }

    /**
     * 获取好友请求列表
     * @param token
     * @return
     */
    @GetMapping(value = "friend/getQuestList/{token}")
    public Object getQuestList(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<UserFriendVO> userFriendVOList = userRelationService.getQuestFriendList(user.getId());
        return JsonResult.success(userFriendVOList);
    }

    /**
     * 获取好友列表
     * @param token
     * @return
     */
    @GetMapping(value = "friend/getList/{token}")
    public Object getFriendList(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<UserFriendVO> userFriendList= userRelationService.getUserFriendList(user.getId());
        return JsonResult.success(userFriendList);
    }

    /**
     * 推荐用户
     * @param token
     * @param pageNum
     * @return
     */
    @GetMapping(value = "user/recommend/{pageNum}/{token}")
    public Object getRecommend(@PathVariable("token") String token, @PathVariable("pageNum") Integer pageNum){
        if (pageNum == null){
            pageNum = 0;
        }
        User user = userService.getUserByToken(token);
        List<FollowAttListVO> userList = userService.getUserRecommend(user.getId(), pageNum);
        return JsonResult.success(userList);
    }
}
