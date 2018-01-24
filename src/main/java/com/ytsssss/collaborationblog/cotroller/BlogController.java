package com.ytsssss.collaborationblog.cotroller;

import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.json.JsonResult;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.vo.BlogVO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Ytsssss on 2018/1/23 19:57
 */
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;
    @PostMapping(value = "/blog/add")
    public Object addBlog(BlogVO blogVO, HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        String value = blogService.addBlog(blogVO, user).toString();
        if (value == null || "".equals(value)){
            return JsonResult.fail(GlobalResultStatus.BLOG_ADD_ERROR);
        }
        return JsonResult.success();
    }
}
