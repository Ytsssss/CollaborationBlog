package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.BlogFavoriteService;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/25 14:58
 */
@RestController
public class BlogFavoriteController {
    private static Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogFavoriteService blogFavoriteService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @PostMapping("/blogFavorite/add")
    public Object addBlogFavorite(@RequestParam("token") String token, @RequestParam("blogId") Long blogId){
        User user= userService.getUserByToken(token);
        int result = blogFavoriteService.addBlogFavorite(user.getId(), blogId);
        if (result <1){
            return JsonResult.fail(GlobalResultStatus.BLOG_FAVORITE_ERROR);
        }
        return JsonResult.success();
    }

    @PostMapping("/blogFavorite/cancel")
    public Object cancelBlogFavorite(@RequestParam("token") String token, @RequestParam("blogId") Long blogId){
        User user= userService.getUserByToken(token);
        int result = blogFavoriteService.cancelBlogFavorite(user.getId(), blogId);
        if (result <1){
            return JsonResult.fail(GlobalResultStatus.BLOG_FAVORITE_CANCEL_ERROR);
        }
        return JsonResult.success();
    }

    @GetMapping("/blogFavorite/getList")
    public Object getBlogFavoriteList(@RequestParam("token") String token){
        User user= userService.getUserByToken(token);
        List<Long> favoriteList = blogFavoriteService.getBlogFavoriteList(user.getId());
        if (favoriteList == null){
            return JsonResult.fail(GlobalResultStatus.BLOG_FAVORITE_LIST_ERROR);
        }else if(favoriteList.isEmpty() || "".equals(favoriteList)){
            return JsonResult.success(Collections.emptyMap());
        }
        return JsonResult.success(blogService.getBlogList(favoriteList));
    }
}
