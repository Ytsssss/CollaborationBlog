package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.UserFans;
import com.ytsssss.collaborationblog.example.UserFansExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFansMapper {
    long countByExample(UserFansExample example);

    int deleteByExample(UserFansExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserFans record);

    int insertSelective(UserFans record);

    List<UserFans> selectByExample(UserFansExample example);

    UserFans selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserFans record, @Param("example") UserFansExample example);

    int updateByExample(@Param("record") UserFans record, @Param("example") UserFansExample example);

    int updateByPrimaryKeySelective(UserFans record);

    int updateByPrimaryKey(UserFans record);
}