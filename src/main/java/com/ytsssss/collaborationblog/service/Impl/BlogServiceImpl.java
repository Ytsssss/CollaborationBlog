package com.ytsssss.collaborationblog.service.Impl;

import com.vdurmont.emoji.EmojiParser;
import com.ytsssss.collaborationblog.constant.status.GlobalResultStatus;
import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.domain.UserRoleRelation;
import com.ytsssss.collaborationblog.exception.GlobalException;
import com.ytsssss.collaborationblog.mapper.*;
import com.ytsssss.collaborationblog.service.BlogCommentService;
import com.ytsssss.collaborationblog.service.BlogLikeService;
import com.ytsssss.collaborationblog.service.BlogService;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.TimeUtil;
import com.ytsssss.collaborationblog.vo.*;

import java.nio.file.Watchable;
import java.util.*;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/23 19:58
 */
@Service
public class BlogServiceImpl implements BlogService{
    private static Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Resource
    private BlogMapper blogMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogLikeService blogLikeService;
    @Resource
    private BlogFavoriteMapper blogFavoriteMapper;
    @Resource
    private BlogLikeMapper blogLikeMapper;
    @Resource
    private UserAttentionMapper userAttentionMapper;
    @Resource
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public Object addBlog(BlogVO blogVO, User user) {
        Date date = new Date();
        Blog blog = new Blog();
        blog.setUserId(user.getId());
        blog.setContent(EmojiParser.parseToHtmlDecimal(blogVO.getContent()));
        blog.setIsComment(blogVO.getIsComment());
        blog.setIsPublic(blogVO.getIsPublic());
        blog.setStatus(blogVO.getStatus());
        blog.setImg(blogVO.getImg());
        blog.setReadTime(0L);
        blog.setTitle(blogVO.getTitle());
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        blog.setPrecontent(blogVO.getPrecontent());
        if (blogVO.getFriendIds() != null && !"".equals(blogVO.getFriendIds())){
            blogMapper.insertSelective(blog);
            List<Long> friendIds = blogVO.getFriendIds();
            List<UserRoleRelation> userRoleRelationList = new ArrayList<>();
            for (Long friendId : friendIds){
                UserRoleRelation userRoleRelation = new UserRoleRelation();
                userRoleRelation.setUserRole(1);
                userRoleRelation.setUpdateTime(new Date());
                userRoleRelation.setCreateTime(new Date());
                userRoleRelation.setUserId(friendId);
                userRoleRelation.setBlogId(blog.getId());
                userRoleRelationList.add(userRoleRelation);
            }
            Integer code = userRoleRelationMapper.insertAllRole(userRoleRelationList);
            logger.info(code.toString());
            return code;
        }
        logger.info(blog.toString());

        return blogMapper.insertSelective(blog);
    }

    @Override
    public Object editBlog(BlogVO blogVO, User user) {
        Date date = new Date();
        Blog blog = new Blog();
        blog.setId(blogVO.getId());
        blog.setContent(EmojiParser.parseToHtmlDecimal(blogVO.getContent()));
        blog.setIsComment(blogVO.getIsComment());
        blog.setIsPublic(blogVO.getIsPublic());
        blog.setTitle(blogVO.getTitle());
        blog.setPrecontent(blogVO.getPrecontent());
        blog.setUpdateTime(date);
        logger.info(blog.toString());

        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    public int deleteBlog(Long blogId) {
        return blogMapper.deleteByPrimaryKey(blogId);
    }

    @Override
    public Blog getBlogInfo(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    public List<Long> getBlogList(Long userId, int range) {
        List<Long> myBlogList = new ArrayList<>();
        List<Long> publicBlogList = new ArrayList<>();
        List<Long> friendBlogList = new ArrayList<>();
        List<Long> draftBlogList = new ArrayList<>();
        List<Long> allBlogList = new ArrayList<>();
        if (range == 1){
            myBlogList = blogMapper.getMyBlogList(userId);
            logger.info("查询结果为："+myBlogList);
            return myBlogList;
        }else if (range == 2){
            publicBlogList = blogMapper.getPublicBlogList(userId);
            logger.info("查询结果为："+publicBlogList);
            return publicBlogList;
        }else if (range == 3){
            friendBlogList = blogMapper.getFriendBlogList(userId);
            logger.info("查询结果为："+friendBlogList);
            return friendBlogList;
        }else if (range == 4){
            draftBlogList = blogMapper.getDraftBlogList(userId);
            logger.info("查询结果为："+draftBlogList);
            return draftBlogList;
        }else if (range == 5){
            //管理博客
            allBlogList = blogMapper.getDraftBlogList(userId);
            allBlogList.addAll(blogMapper.getMyBlogList(userId));
            logger.info("查询结果为："+allBlogList);
            return allBlogList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<Blog> getBlogList(List<Long> blogIdList) {
        List<Blog> blogList = blogMapper.getBlogListByIds(blogIdList);
        logger.info("查询结果为："+blogList);
        return blogList;
    }

    @Override
    public List<HomeBlogVO> getHomeBlogList(List<Long> blogIdList){
        List<HomeBlogVO> homeBlogList = new ArrayList<HomeBlogVO>();
        if (blogIdList.size() != 0){
            List<Blog> blogList = getBlogList(blogIdList);
            for (Blog blog: blogList){
                User user = userService.getUserInfo(blog.getUserId());
                int commentCount = blogCommentService.getBlogCommentCount(blog.getId());
                int likeCount = blogLikeService.getBlogLikeCount(blog.getId());
                HomeBlogVO homeBlogVO = new HomeBlogVO();
                homeBlogVO.setId(blog.getId());
                homeBlogVO.setContent(EmojiParser.parseToUnicode(blog.getContent()));
                homeBlogVO.setCreateTime(TimeUtil.changeTimeToString(blog.getCreateTime()));
                homeBlogVO.setImg(blog.getImg());
                homeBlogVO.setTitle(blog.getTitle());
                homeBlogVO.setUpdateTime(TimeUtil.changeTimeToString(blog.getUpdateTime()));
                homeBlogVO.setUserId(blog.getUserId());
                homeBlogVO.setReadTime(blog.getReadTime());
                homeBlogVO.setCommentCount(commentCount);
                homeBlogVO.setUserName(user.getName());
                homeBlogVO.setUserAvatar(user.getAvatar());
                homeBlogVO.setLikeCount(likeCount);
                homeBlogList.add(homeBlogVO);
            }
        }else {
            homeBlogList = Collections.emptyList();
        }
        return homeBlogList;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long blogId,Long userId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        User user = userService.getUserInfo(blog.getUserId());
        boolean isLike = (blogLikeMapper.isLike(blogId, userId) == 1);
        boolean isFollow = (userAttentionMapper.isAttention(userId, blog.getUserId()) == 1);
        boolean isFavorite = (blogFavoriteMapper.isFavorite(blogId, userId) == 1);
        BlogDetailVO blogDetailVO = new BlogDetailVO();
        blogDetailVO.setId(blogId);
        blogDetailVO.setContent(blog.getContent());
        blogDetailVO.setCreateTime(TimeUtil.changeTimeToString(blog.getCreateTime()));
        blogDetailVO.setReadTime(blog.getReadTime());
        blogDetailVO.setTitle(blog.getTitle());
        blogDetailVO.setUserId(blog.getUserId());
        blogDetailVO.setUserAvatar(user.getAvatar());
        blogDetailVO.setUserName(user.getName());
        blogDetailVO.setFollow(isFollow);
        blogDetailVO.setLike(isLike);
        blogDetailVO.setFavorite(isFavorite);
        blogDetailVO.setLikeCount(blogLikeService.getBlogLikeCount(blogId));
        return blogDetailVO;
    }

    @Override
    public int updateReadTime(Long blogId, Long readTime) throws Exception{
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setReadTime(readTime);
        int code = blogMapper.updateByPrimaryKeySelective(blog);
        if (code!=1){
            throw new GlobalException(GlobalResultStatus.PARAM_ERROR);
        }
        return code;
    }

    @Override
    public List<BlogManageVO> searchByName(String name, int tag, String token) {
        List<BlogManageVO> searchBlogVOList = new ArrayList<>();
        List<BlogManageVO> searchByTitle = new ArrayList<>();
        List<BlogManageVO> searchByUser = new ArrayList<>();
        User user = userService.getUserByToken(token);
        if (tag == 1){
            searchByTitle = blogMapper.searchBlogByTitle(name, user.getId());
            for (BlogManageVO blogManageVO : searchByTitle){
                Long userId = blogManageVO.getUserId();
                User blogUser = userService.getUserInfo(userId);
                blogManageVO.setUserName(blogUser.getName());
            }
            return searchByTitle;
        }else if (tag == 2){
            searchByUser = blogMapper.searchBlogByName(name, user.getId());
            return searchByUser;
        }else {
            searchBlogVOList.addAll(searchByTitle);
            searchBlogVOList.addAll(searchByUser);
            return searchBlogVOList;
        }
    }

    @Override
    public UserCountVO getUserCount(Long userId) {
        UserCountVO userCountVO = new UserCountVO();
        userCountVO.setBlogCount(blogMapper.getBlogCount(userId));
        userCountVO.setLikeCount(blogLikeMapper.getLikeCountByUser(userId));
        userCountVO.setFollowCount(userAttentionMapper.getFollowCount(userId));
        userCountVO.setFansCount(userAttentionMapper.getFansCount(userId));
        logger.info(userCountVO.toString());
        return userCountVO;
    }

    @Override
    public int getBlogCountByUser(Long userId) {
        int blogCount = blogMapper.getBlogCount(userId);
        return blogCount;
    }

    @Override
    public List<Blog> getHotBlogList() {
        List<Blog> blogList = blogMapper.getHotBlogList();
        logger.info(blogList.toString());
        return blogList;
    }

    @Override
    public List<BlogWeekVO> getWeekBlogList(Long userId) {
        List<BlogWeekVO> blogWeekVOList = blogMapper.getWeekBlogList(userId);
        List<BlogWeekVO> weekList = new ArrayList<>();
        for (int i=1; i<8; i++){
            BlogWeekVO blogWeekVO = new BlogWeekVO();
            int tag = 0;
            for (BlogWeekVO blogWeekVO1 : blogWeekVOList){
                if (blogWeekVO1.getTime().equals(TimeUtil.getCurDate(i))){
                    blogWeekVO.setCount(blogWeekVO1.getCount());
                    blogWeekVO.setTime(blogWeekVO1.getTime());
                    tag = 1;
                    break;
                }
            }
            if (tag == 0){
                blogWeekVO.setTime(TimeUtil.getCurDate(i));
                blogWeekVO.setCount(0);
            }
            weekList.add(blogWeekVO);
        }
        return weekList;
    }

    @Override
    public List<HomeBlogVO> getFriendBlogList(Long userId) {
        List<Long> friendBlogIds = userRoleRelationMapper.getFriendBlogIds(userId);
        if (friendBlogIds.isEmpty() || friendBlogIds.size() == 0){
            return Collections.emptyList();
        }
        List<HomeBlogVO> friendBlogList = getHomeBlogList(friendBlogIds);
        return friendBlogList;
    }

    private void insertUserRole(List<Long> friendIds, Long blogId){
        for (Long friendId : friendIds){
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setBlogId(blogId);
            userRoleRelation.setCreateTime(new Date());
            userRoleRelation.setUserId(friendId);
            userRoleRelation.setUpdateTime(new Date());
            userRoleRelation.setUserRole(1);//好友可以查看
            userRoleRelationMapper.insertSelective(userRoleRelation);
        }
    }
}
