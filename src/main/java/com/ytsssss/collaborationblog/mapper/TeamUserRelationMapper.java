package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.TeamUserRelation;
import com.ytsssss.collaborationblog.example.TeamUserRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamUserRelationMapper {
    long countByExample(TeamUserRelationExample example);

    int deleteByExample(TeamUserRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TeamUserRelation record);

    int insertSelective(TeamUserRelation record);

    List<TeamUserRelation> selectByExample(TeamUserRelationExample example);

    TeamUserRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TeamUserRelation record, @Param("example") TeamUserRelationExample example);

    int updateByExample(@Param("record") TeamUserRelation record, @Param("example") TeamUserRelationExample example);

    int updateByPrimaryKeySelective(TeamUserRelation record);

    int updateByPrimaryKey(TeamUserRelation record);
}