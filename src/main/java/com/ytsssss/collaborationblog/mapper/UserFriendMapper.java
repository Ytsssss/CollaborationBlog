package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.UserFriend;
import com.ytsssss.collaborationblog.example.UserFriendExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserFriendMapper {
    long countByExample(UserFriendExample example);

    int deleteByExample(UserFriendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    List<UserFriend> selectByExample(UserFriendExample example);

    UserFriend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserFriend record, @Param("example") UserFriendExample example);

    int updateByExample(@Param("record") UserFriend record, @Param("example") UserFriendExample example);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    /**
     * 删除好友
     * @param userId
     * @param friendId
     * @return
     */
    @Delete("delete from user_friend where user_id = #{userId} and friend_id = #{friendId}")
    int deleteByUserAndFriendId(@Param("userId")Long userId, @Param("friendId") Long friendId);

    /**
     * 确定好友关系
     * @param userId
     * @param friendId
     * @param status
     * @return
     */
    @Update("update user_friend set status = #{status} where user_id = #{userId} and friend_id = #{friendId}")
    int confirmUserFriend(@Param("userId")Long userId, @Param("friendId") Long friendId, @Param("status") int status);

    /**
     * 获取用户好友列表
     * @param userId
     * @return
     */
    @Select("select id from user_friend where user_id = #{userId} and status=0")
    List<Long> getUserFriendList(@Param("userId") Long userId);

    /**
     * 获取请求添加好友列表
     * @param userId
     * @return
     */
    @Select("select id from user_friend where friend_id = #{userId} and status=1")
    List<Long> getQuestFriendList(@Param("userId") Long userId);
}