package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.BlogLikeService;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import java.util.Collections;
import java.util.List;

import com.ytsssss.collaborationblog.vo.BlogLikeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Ytsssss on 2018/1/25 18:21
 */
@RestController
public class BlogLikeController {
    private static Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogLikeService blogLikeService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    /**
     * 博客点赞
     * @param token
     * @param blogId
     * @return
     */
    @PostMapping("/blogLike/add")
    public Object addBlogLike(@RequestParam("token") String token, @RequestParam("blogId") Long blogId){
        User user= userService.getUserByToken(token);
        int result = blogLikeService.addBlogLike(blogId, user.getId());
        if (result <1){
            return JsonResult.fail(GlobalResultStatus.BLOG_LIKE_ERROR);
        }
        return JsonResult.success();
    }

    /**
     * 取消博客点赞
     * @param token
     * @param blogId
     * @return
     */
    @PostMapping("/blogLike/cancel")
    public Object cancelBlogLike(@RequestParam("token") String token, @RequestParam("blogId") Long blogId){
        User user= userService.getUserByToken(token);
        int result = blogLikeService.cancelBlogLike(blogId, user.getId());
        if (result <1){
            return JsonResult.fail(GlobalResultStatus.BLOG_LIKE_CANCEL_ERROR);
        }
        return JsonResult.success();
    }

    /**
     * 获取用户点赞的列表
     * @param token
     * @return
     */
    @GetMapping("/blogLike/getList")
    public Object getBlogLikeList(@RequestParam("token") String token){
        User user= userService.getUserByToken(token);
        List<Long> likeList = blogLikeService.getBlogLikeList(user.getId());
        if (likeList == null){
            return JsonResult.fail(GlobalResultStatus.BLOG_LIKE_LIST_ERROR);
        }else if(likeList.isEmpty() || "".equals(likeList)){
            return JsonResult.success(Collections.emptyMap());
        }
        return JsonResult.success(blogService.getBlogList(likeList));
    }

    /**
     * 获取点赞我文章列表
     * @param token
     * @return
     */
    @GetMapping(value = "/blogLike/getBeLikeList/{token}")
    public Object getBeLikeList(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<BlogLikeVO> blogLikeVOList = blogLikeService.getBeLikeList(user.getId());
        return JsonResult.success(blogLikeVOList);
    }
}
