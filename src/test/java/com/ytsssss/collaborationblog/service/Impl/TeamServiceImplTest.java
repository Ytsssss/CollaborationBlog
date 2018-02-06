package com.ytsssss.collaborationblog.service.Impl;

import static org.junit.Assert.*;

import com.ytsssss.collaborationblog.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by Ytsssss on 2018/2/1 10:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceImplTest {
    @Autowired
    private TeamService teamService;
    @Test
    public void addTeam() {
        teamService.addTeam("醉春风2", "这是一个最好的团队", 9L ,0,0);
    }

    @Test
    public void deleteTeam() {
        teamService.deleteTeam(2L);
    }

    @Test
    public void getChargeTeamList() {
        teamService.getChargeTeamList(9L);
    }

    @Test
    public void getPartTeamList() {
        teamService.getPartTeamList(10L);
    }

    @Test
    public void addUserTeam() {
        teamService.addUserTeam(10L,2L);
    }

    @Test
    public void deleteUserTeam() {
        teamService.deleteUserTeam(10L,2L);
    }

    @Test
    public void getTeamInfo() {
        teamService.getTeamInfo(1L);
    }

    @Test
    public void getTeamPartList() {
        teamService.getTeamPartList(1L);
    }
}