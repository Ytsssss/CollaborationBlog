package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.BlogComment;
import com.ytsssss.collaborationblog.example.BlogCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogCommentMapper {
    long countByExample(BlogCommentExample example);

    int deleteByExample(BlogCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    List<BlogComment> selectByExample(BlogCommentExample example);

    BlogComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogComment record, @Param("example") BlogCommentExample example);

    int updateByExample(@Param("record") BlogComment record, @Param("example") BlogCommentExample example);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);

    int deleteBycommentList(@Param("commentList") List<Long> commentList);
}