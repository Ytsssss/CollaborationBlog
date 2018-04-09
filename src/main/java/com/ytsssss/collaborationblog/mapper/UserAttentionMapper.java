package com.ytsssss.collaborationblog.mapper;

import com.ytsssss.collaborationblog.domain.UserAttention;
import com.ytsssss.collaborationblog.example.UserAttentionExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserAttentionMapper {
    long countByExample(UserAttentionExample example);

    int deleteByExample(UserAttentionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAttention record);

    int insertSelective(UserAttention record);

    List<UserAttention> selectByExample(UserAttentionExample example);

    UserAttention selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAttention record, @Param("example") UserAttentionExample example);

    int updateByExample(@Param("record") UserAttention record, @Param("example") UserAttentionExample example);

    int updateByPrimaryKeySelective(UserAttention record);

    int updateByPrimaryKey(UserAttention record);

    /**
     * 取消用户关注
     * @param userId
     * @param attentionId
     * @return
     */
    @Delete("delete from user_attention where user_id = #{userId} and attention_id = #{attentionId}")
    int deleteByUserAndAttentionId(@Param("userId") Long userId, @Param("attentionId")Long attentionId);

    /**
     * 查找用户关注列表
     * @param userId
     * @return
     */
    List<Long> getUserAttentionList(@Param("userId") Long userId);

    /**
     * 查找用户粉丝列表
     * @param attentionId
     * @return
     */
    List<Long> getUserFansList(@Param("attentionId") Long attentionId);

    /**
     * 判断是否关注
     * @param userId
     * @param attentionId
     * @return
     */
    int isAttention(@Param("userId") Long userId, @Param("attentionId")Long attentionId);
}