package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.mapper.BlogMapper;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.*;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/23 19:57
 */
@RestController
public class BlogController {
    private static Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Resource
    private BlogMapper blogMapper;

    /**
     * 新增博客
     * @param blogVO
     * @param bindingResult
     * @param token
     * @return
     */
    @PostMapping(value = "/blog/add")
    public Object addBlog(@Valid BlogVO blogVO, BindingResult bindingResult,
                          @RequestParam("token") String token)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getFieldError().getDefaultMessage());
            return JsonResult.fail(GlobalResultStatus.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        User user = userService.getUserByToken(token);
        String value = blogService.addBlog(blogVO, user).toString();
        if (value == null || "".equals(value)){
            return JsonResult.fail(GlobalResultStatus.BLOG_ADD_ERROR);
        }
        return JsonResult.success();
    }

    /**
     * 修改博客
     * @param blogVO
     * @param bindingResult
     * @param token
     * @return
     */
    @PostMapping(value = "blog/edit")
    public Object editBlog(@Valid BlogVO blogVO, BindingResult bindingResult, @RequestParam("token") String token){
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getFieldError().getDefaultMessage());
            return JsonResult.fail(GlobalResultStatus.PARAM_ERROR);
        }
        User user = userService.getUserByToken(token);
        String value = blogService.editBlog(blogVO, user).toString();
        if (value == null || "".equals(value)){
            return JsonResult.fail(GlobalResultStatus.BLOG_EDIT_ERROR);
        }
        return JsonResult.success();
    }

    /**
     * 删除博客
     * @param blogId
     * @return
     */
    @PostMapping(value = "blog/delete")
    public Object deleteBlog(@RequestParam("blogId") Long blogId){
        int value = blogService.deleteBlog(blogId);
        if (value < 1){
            return JsonResult.fail(GlobalResultStatus.BLOG_DELETE_ERROR);
        }
        return JsonResult.success();
    }

    /**
     * 获取博客列表
     * @param token
     * @param range
     * @return
     */
    @GetMapping(value = "blog/getList/{range}/{token}")
    public Object getList(@PathVariable("token") String token, @PathVariable("range") int range){
        User user = userService.getUserByToken(token);
        List<Long> blogList = blogService.getBlogList(user.getId(), range);
        List<HomeBlogVO> homeBlogList = blogService.getHomeBlogList(blogList);
        return JsonResult.success(homeBlogList);
    }

    /**
     * 获取博客详情
     * @param blogId
     * @return
     */
    @GetMapping(value = "blog/getDetail/{blogId}/{token}")
    public Object getDetail(@PathVariable("blogId") Long blogId, @PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        logger.info(blogService.getBlogDetail(blogId, user.getId()).toString());
        return JsonResult.success(blogService.getBlogDetail(blogId, user.getId()));
    }

    /**
     * 获取博客信息（用于修改博客）
     * @param blogId
     * @return
     */
    @GetMapping(value = "blog/getBlog/{blogId}")
    public Object getBlog(@PathVariable("blogId")Long blogId){
        return JsonResult.success(blogService.getBlogInfo(blogId));
    }

    /**
     * 更新阅读次数
     * @param readTime
     * @param blogId
     * @return
     * @throws Exception
     */
    @PostMapping(value = "blog/update/readTime")
    public Object updateReadTime(@RequestParam("readTime") Long readTime,
                                 @RequestParam("blogId") Long blogId)throws Exception{
        blogService.updateReadTime(blogId, readTime);
        return JsonResult.success();
    }

    /**
     * 搜索blog
     * @param name
     * @param tag
     * @param token
     * @return
     */
    @PostMapping(value = "blog/searchByName")
    public Object searchByName(@RequestParam("name") String name, @RequestParam("tag") int tag,
                               @RequestParam("token") String token){
        List<BlogManageVO> homeBlogVOS = blogService.searchByName(name, tag, token);
        return JsonResult.success(homeBlogVOS);
    }

    /**
     * 获取用户的文章，关注，粉丝数量
     * @param userId
     * @return
     */
    @GetMapping(value = "blog/getUserCount/{userId}")
    public Object getUserCount(@PathVariable("userId") Long userId){
        UserCountVO userCountVO = blogService.getUserCount(userId);
        return JsonResult.success(userCountVO);
    }

    /**
     * 获取当前用户的文章，关注，粉丝数量
     * @param token
     * @return
     */
    @GetMapping(value = "blog/getOwnUserCount/{token}")
    public Object getOwnUserCount(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        UserCountVO userCountVO = blogService.getUserCount(user.getId());
        return JsonResult.success(userCountVO);
    }

    /**
     * 获取每周热门文章
     * @return
     */
    @GetMapping(value = "blog/getHotBlogList")
    public Object getHotBlogList(){
        List<Blog> hotBlogList = blogService.getHotBlogList();
        return JsonResult.success(hotBlogList);
    }

    /**
     * 获取一周内的文章发布数
     * @param token
     * @return
     */
    @GetMapping(value = "blog/getWeekList/{token}")
    public Object getWeekBlogList(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<BlogWeekVO> blogWeekVOList = blogService.getWeekBlogList(user.getId());
        return JsonResult.success(blogWeekVOList);
    }

    /**
     * 获取可查看的好友博客列表
     * @param token
     * @return
     */
    @GetMapping(value = "blog/getFriendBlogList/{token}")
    public Object getFriendBlog(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        List<HomeBlogVO> friendBlogList = blogService.getFriendBlogList(user.getId());
        return JsonResult.success(friendBlogList);
    }
}
