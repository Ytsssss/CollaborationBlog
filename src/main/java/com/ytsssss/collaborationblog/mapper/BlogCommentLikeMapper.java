package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.BlogCommentLike;
import com.ytsssss.collaborationblog.example.UserCommentLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogCommentLikeMapper {
    long countByExample(UserCommentLikeExample example);

    int deleteByExample(UserCommentLikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogCommentLike record);

    int insertSelective(BlogCommentLike record);

    List<BlogCommentLike> selectByExample(UserCommentLikeExample example);

    BlogCommentLike selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogCommentLike record, @Param("example") UserCommentLikeExample example);

    int updateByExample(@Param("record") BlogCommentLike record, @Param("example") UserCommentLikeExample example);

    int updateByPrimaryKeySelective(BlogCommentLike record);

    int updateByPrimaryKey(BlogCommentLike record);

    int deleteByBlogCommentAndUserId(@Param("blogCommentId") Long blogCommentId, @Param("userId") Long userId);

    /**
     * 统计评论点赞数
     * @param blogCommentId
     * @return
     */
    int getCommentCount(@Param("blogCommentId") Long blogCommentId);

    /**
     * 判断该用户是否点赞
     * @param blogCommentId
     * @param userId
     * @return
     */
    int isLikeComment(@Param("blogCommentId") Long blogCommentId, @Param("userId") Long userId);
}