package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.Blog;
import com.ytsssss.collaborationblog.example.BlogExample;
import java.util.List;

import com.ytsssss.collaborationblog.vo.BlogManageVO;
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

    List<Long> getPublicBlogList(@Param("userId") Long userId);

    List<Long> getFriendBlogList(@Param("userId") Long userId);

    List<Long> getDraftBlogList(@Param("userId") Long userId);

    List<Blog> getHotBlogList();

    List<Blog> getBlogListByIds(@Param("blogIdList") List<Long> blogIdList);

    List<BlogManageVO> searchBlogByTitle(@Param("title") String title, @Param("userId") Long userId);

    List<BlogManageVO> searchBlogByName(@Param("userName") String userName, @Param("userId") Long userId);

    int getBlogCount(@Param("userId") Long userId);
}