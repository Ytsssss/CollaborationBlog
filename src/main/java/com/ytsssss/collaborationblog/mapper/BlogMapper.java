package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.example.BlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogMapper {
    long countByExample(BlogExample example);

    int deleteByExample(BlogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Long> getMyBlogList(@Param("userId") Long userId);

    List<Long> getPublicBlobList(@Param("userId") Long userId);

    List<Long> getFriendBlobList(@Param("userId") Long userId);

    List<Long> getDraftBlogList(@Param("userId") Long userId);

    List<Blog> getBlogListByIds(@Param("blogIdList") List<Long> blogIdList);
}