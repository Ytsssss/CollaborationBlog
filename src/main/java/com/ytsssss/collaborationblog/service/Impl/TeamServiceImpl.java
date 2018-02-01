package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.Team;
import com.ytsssss.collaborationblog.domain.TeamUserRelation;
import com.ytsssss.collaborationblog.mapper.TeamMapper;
import com.ytsssss.collaborationblog.mapper.TeamUserRelationMapper;
import com.ytsssss.collaborationblog.service.TeamService;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Create by Ytsssss on 2018/1/31 16:45
 */
@Service
public class TeamServiceImpl implements TeamService{
    private static Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    @Resource
    private TeamMapper teamMapper;
    @Resource
    private TeamUserRelationMapper teamUserRelationMapper;
    @Override
    public int addTeam(String name, String describe, Long userId, int isPublic, int status) {
        Team team = new Team();
        team.setDescribe(describe);
        team.setName(name);
        team.setIsPublic(isPublic);
        team.setStatus(status);
        team.setUserId(userId);
        logger.info(team.toString());
        return teamMapper.insertSelective(team);
    }

    @Override
    public int deleteTeam(Long id) {
        return teamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Long> getChargeTeamList(Long userId) {
        return teamMapper.getChargeTeamList(userId);
    }

    @Override
    public List<Long> getPartTeamList(Long userId) {
        return teamUserRelationMapper.getPartTeamList(userId);
    }

    @Override
    public int addUserTeam(Long userId, Long teamId) {
        TeamUserRelation teamUserRelation = new TeamUserRelation();
        teamUserRelation.setUserId(userId);
        teamUserRelation.setTeamId(teamId);
        teamUserRelation.setStatus(0);

        return teamUserRelationMapper.insertSelective(teamUserRelation);
    }

    @Override
    public int deleteUserTeam(Long userId, Long teamId) {
        return teamUserRelationMapper.deleteUserTeam(userId, teamId);
    }

    @Override
    public Team getTeamInfo(Long teamId) {
        return teamMapper.selectByPrimaryKey(teamId);
    }

    @Override
    public List<Long> getTeamPartList(Long teamId) {
        return teamUserRelationMapper.getTeamPartList(teamId);
    }
}
