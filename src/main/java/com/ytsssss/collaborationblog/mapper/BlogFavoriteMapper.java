package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.BlogFavorite;
import com.ytsssss.collaborationblog.example.BlogFavoriteExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlogFavoriteMapper {
    long countByExample(BlogFavoriteExample example);

    int deleteByExample(BlogFavoriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogFavorite record);

    int insertSelective(BlogFavorite record);

    List<BlogFavorite> selectByExample(BlogFavoriteExample example);

    BlogFavorite selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogFavorite record, @Param("example") BlogFavoriteExample example);

    int updateByExample(@Param("record") BlogFavorite record, @Param("example") BlogFavoriteExample example);

    int updateByPrimaryKeySelective(BlogFavorite record);

    int updateByPrimaryKey(BlogFavorite record);

    int deleteByBlogAndUserId(@Param("blogId") Long blogId, @Param("userId") Long userId);

    List<Long> getBlogListByUserId(@Param("userId") Long userId);

    /**
     * 判断是否收藏
     * @param blogId
     * @param userId
     * @return
     */
    int isFavorite(@Param("blogId") Long blogId, @Param("userId") Long userId);

    List<BlogFavorite> getBeFavoList(@Param("blogIds") List<Long> blogIds);
}