package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.UserCommentLike;
import com.ytsssss.collaborationblog.example.UserCommentLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCommentLikeMapper {
    long countByExample(UserCommentLikeExample example);

    int deleteByExample(UserCommentLikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCommentLike record);

    int insertSelective(UserCommentLike record);

    List<UserCommentLike> selectByExample(UserCommentLikeExample example);

    UserCommentLike selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCommentLike record, @Param("example") UserCommentLikeExample example);

    int updateByExample(@Param("record") UserCommentLike record, @Param("example") UserCommentLikeExample example);

    int updateByPrimaryKeySelective(UserCommentLike record);

    int updateByPrimaryKey(UserCommentLike record);
}