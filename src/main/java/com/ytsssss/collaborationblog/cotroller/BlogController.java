package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.mapper.BlogMapper;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.BlogDetailVO;
import com.ytsssss.collaborationblog.vo.BlogVO;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

import com.ytsssss.collaborationblog.vo.HomeBlogVO;
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
    public Object addBlog(@Valid BlogVO blogVO, BindingResult bindingResult, @RequestParam("token") String token){
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getFieldError().getDefaultMessage());
            return JsonResult.fail(GlobalResultStatus.PARAM_ERROR);
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
        String value = blogService.addBlog(blogVO, user).toString();
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
    @PostMapping(value = "blog/delete/{blogId}")
    public Object deleteBlog(@PathVariable("blogId") Long blogId){
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
    @GetMapping(value = "blog/getDetail/{blogId}")
    public Object getDetail(@PathVariable("blogId") Long blogId){
        logger.info(blogService.getBlogDetail(blogId).toString());
        return JsonResult.success(blogService.getBlogDetail(blogId));
    }
}
