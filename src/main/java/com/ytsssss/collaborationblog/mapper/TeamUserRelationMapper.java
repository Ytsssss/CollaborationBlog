package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.TeamUserRelation;
import com.ytsssss.collaborationblog.example.TeamUserRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Delete("delete from team_user_relation where user_id = #{userId} and team_id = #{teamId}")
    int deleteUserTeam(@Param("userId")Long userId, @Param("teamId")Long teamId);

    @Select("select user_id from team_user_relation where team_id = #{teamId} and status=0")
    List<Long> getTeamPartList(@Param("teamId") Long teamId);

    @Select("select team_id from team_user_relation where user_id = #{userId} and status=0")
    List<Long> getPartTeamList(@Param("userId") Long userId);
}