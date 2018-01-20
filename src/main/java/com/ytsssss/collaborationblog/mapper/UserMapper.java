package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.example.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record,
      @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 判断用户是否存在
     * @param accountId
     * @param password
     * @return
     */
    //@Select("select id from user where account_id = #{accountId} and password = #{password}")
    Long selectByaccountIdAndPassword(@Param("accountId") String accountId, @Param("password") String password);
}