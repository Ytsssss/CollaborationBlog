package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.vo.BlogCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by Ytsssss on 2018/1/25 19:49
 */
@RestController
public class BlogCommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 添加评论，支持多级评论
     * @param blogId
     * @param token
     * @param comment
     * @param replyUserId
     * @param replyCommentId
     * @return
     */
    @PostMapping("blogComment/add")
    public Object addComment(@RequestParam("blogId") Long blogId, @RequestParam("token") String token,
                             @RequestParam("comment") String comment,
                             @RequestParam("replyUserId") Long replyUserId,
                             @RequestParam("replyCommentId") Long replyCommentId){
        User user = userService.getUserByToken(token);
        int code = blogCommentService.addComment(blogId, user.getId(), replyCommentId, replyUserId, comment);
        if (code<1){
            return JsonResult.fail(GlobalResultStatus.PARAM_ERROR);
        }
        return JsonResult.success();
    }

    /**
     * 获取博客评论列表
     * @param blogId
     * @param token
     * @return
     */
    @GetMapping("blogComment/getList/{token}/{blogId}")
    public Object getCommentList(@PathVariable("blogId") Long blogId, @PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<BlogCommentVO> blogCommentVOList = blogCommentService.getBlogCommentList(blogId, user);
        return JsonResult.success(blogCommentVOList);
    }

    /**
     * 点赞博客评论
     * @param commentId
     * @param token
     * @return
     */
    @PostMapping(value = "blogComment/like")
    public Object blogCommentLike(@RequestParam("commentId") Long commentId,
                                  @RequestParam("token") String token){
        User user = userService.getUserByToken(token);
        blogCommentService.addCommentLike(commentId, user.getId());
        return JsonResult.success();
    }

    /**
     * 取消点赞博客评论
     * @param commentId
     * @param token
     * @return
     */
    @PostMapping(value = "blogComment/cancelLike")
    public Object blogCommentCancelLike(@RequestParam("commentId") Long commentId,
                                        @RequestParam("token") String token){
        User user = userService.getUserByToken(token);
        blogCommentService.cancelCommentLike(commentId, user.getId());
        return JsonResult.success();
    }
}
