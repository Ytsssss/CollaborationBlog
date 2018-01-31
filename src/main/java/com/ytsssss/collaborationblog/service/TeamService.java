package com.ytsssss.collaborationblog.service;

/**
 * Create by Ytsssss on 2018/1/31 16:45
 */
public interface TeamService {
    int addTeam(String name, String describe, Long userId, int isPublic, int status);
    int deleteTeam(Long id);
}
