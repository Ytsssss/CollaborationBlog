package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.BlogLike;
import com.ytsssss.collaborationblog.example.BlogLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogLikeMapper {
    long countByExample(BlogLikeExample example);

    int deleteByExample(BlogLikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogLike record);

    int insertSelective(BlogLike record);

    List<BlogLike> selectByExample(BlogLikeExample example);

    BlogLike selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogLike record, @Param("example") BlogLikeExample example);

    int updateByExample(@Param("record") BlogLike record, @Param("example") BlogLikeExample example);

    int updateByPrimaryKeySelective(BlogLike record);

    int updateByPrimaryKey(BlogLike record);

    int deleteByBlogAndUserId(@Param("blogId") Long blogId, @Param("userId") Long userId);

    List<Long> getBlogListByUserId(@Param("userId") Long userId);

    int getBlogLikeCount(@Param("blogId") Long blogId);

    /**
     * 判断是否点赞
     * @param blogId
     * @param userId
     * @return
     */
    int isLike(@Param("blogId") Long blogId, @Param("userId") Long userId);
}