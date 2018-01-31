package com.ytsssss.collaborationblog.service.Impl;

import com.ytsssss.collaborationblog.domain.Team;
import com.ytsssss.collaborationblog.mapper.TeamMapper;
import com.ytsssss.collaborationblog.service.TeamService;
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
}
