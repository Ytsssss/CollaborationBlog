package com.ytsssss.collaborationblog.service;

import com.ytsssss.collaborationblog.domain.Team;
import java.util.List;

/**
 * Create by Ytsssss on 2018/1/31 16:45
 */
public interface TeamService {

    /**
     * 新增团队
     * @param name
     * @param describe
     * @param userId
     * @param isPublic
     * @param status
     * @return
     */
    int addTeam(String name, String describe, Long userId, int isPublic, int status);

    /**
     * 删除团队
     * @param id
     * @return
     */
    int deleteTeam(Long id);

    /**
     * 查看负责的团队列表
     * @param userId
     * @return
     */
    List<Long> getChargeTeamList(Long userId);

    /**
     * 查看参与的团队列表
     * @param userId
     * @return
     */
    List<Long> getPartTeamList(Long userId);

    /**
     * 添加团队成员
     * @param userId
     * @param teamId
     * @return
     */
    int addUserTeam(Long userId, Long teamId);

    /**
     * 删除团队成员（负责人权限）
     * @param userId
     * @param teamId
     * @return
     */
    int deleteUserTeam(Long userId, Long teamId);

    /**
     * 获取团队详情
     * @param teamId
     * @return
     */
    Team getTeamInfo(Long teamId);

    /**
     * 获取团队成员（不包括负责人）
     * @param teamId
     * @return
     */
    List<Long> getTeamPartList(Long teamId);
}
