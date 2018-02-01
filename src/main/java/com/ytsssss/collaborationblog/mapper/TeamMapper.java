package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.Team;
import com.ytsssss.collaborationblog.example.TeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeamMapper {
    long countByExample(TeamExample example);

    int deleteByExample(TeamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Team record);

    int insertSelective(Team record);

    List<Team> selectByExample(TeamExample example);

    Team selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Team record, @Param("example") TeamExample example);

    int updateByExample(@Param("record") Team record, @Param("example") TeamExample example);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);

    @Select("select team_id from team where user_id = #{userId} and status=0")
    List<Long> getChargeTeamList(@Param("userId")Long userId);
}