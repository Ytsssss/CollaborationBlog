package com.ytsssss.collaborationblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.ytsssss.collaborationblog.constant.GlobalConstant;
import com.ytsssss.collaborationblog.domain.User;
import com.ytsssss.collaborationblog.mapper.UserAttentionMapper;
import com.ytsssss.collaborationblog.mapper.UserMapper;
import com.ytsssss.collaborationblog.service.UserService;
import com.ytsssss.collaborationblog.util.RandomUtil;
import com.ytsssss.collaborationblog.util.SHAUtil;
import javax.annotation.Resource;

import com.ytsssss.collaborationblog.vo.FollowAttListVO;
import com.ytsssss.collaborationblog.vo.UserChangeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Ytsssss on 2018/1/20 14:07
 */
@Service
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAttentionMapper userAttentionMapper;
    @Override
    public User getUserInfo(String accountId, String password) {
        User user = userMapper.getUserInfoByIdAndPassword(accountId, password);
        return user;
    }

    @Override
    public User getUserInfo(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Long login(String accountId, String password) {
        //使用SHA 进行密码加密
        String encode = SHAUtil.getResult(password);
        Long userId = userMapper.selectByaccountId(accountId);
        if (userId == null || "".equals(userId)){
            return -2L;
        }
        // 如果账号存在，查看账号密码是否正确
        userId = userMapper.selectByaccountIdAndPassword(accountId, encode);
        if (userId == null || "".equals(userId)){
            return -1L;
        }
        return userId;
    }

    @Override
    public Long register(String accountId, String password, String verifyCode, String mailAddress){
        Long userId = userMapper.selectByaccountId(accountId);
        if (userId != null){
            return -1L;
        }
        String code = stringRedisTemplate.opsForValue().get(GlobalConstant.MAILCODE);
        logger.info("从redis中获取的验证码为： "+code);
        if (code == null || !code.equals(verifyCode)){
            return -2L;
        }
        //使用SHA 进行密码加密
        String encode = SHAUtil.getResult(password);

        User user = new User();
        user.setaccountId(accountId);
        user.setAvatar("http://omfs7yds0.bkt.clouddn.com/timg.jpg");
        user.setEmail(mailAddress);
        user.setPassword(encode);
        user.setName(accountId);
        logger.info(user.toString());

        return (long)userMapper.insertSelective(user);
    }

    @Override
    public String getTokenByUser(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        String token= RandomUtil.getRandomNum(20);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        try {
            operations.set(token, user);
            logger.info("将用户信息存入redis成功！ token = "+token+"user = "+user.toString());
        }catch (Exception e){
            logger.error("将用户信息存入redis失败 "+e.getMessage());
        }
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        if (redisTemplate.hasKey(token)){
            logger.info("redis中存在token"+token);
        }else {
            logger.info("redis中不存在token");
        }
        User user = (User) redisTemplate.opsForValue().get(token);
        logger.info("用户信息为："+user.toString());
        return user;
    }

    @Override
    public int changeUserInfo(UserChangeVO user, String token) {
        logger.info(user.toString());
        User newUser = getUserByToken(token);
        newUser.setAvatar(user.getAvatar());
        newUser.setName(user.getName());
        newUser.setBirthday(user.getBirthday());
        newUser.setSchool(user.getSchool());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setLocation(user.getLocation());
        newUser.setIntroduce(user.getIntroduce());

        int code = userMapper.updateByPrimaryKeySelective(newUser);
        updateUserByToken(newUser.getId(), token);
        return code;
    }

    @Override
    public List<FollowAttListVO> getUserRecommend(Long userId, int pageNum) {
        if (pageNum > 0){
            PageHelper.startPage(pageNum, 3);
        }
        List<User> userList = userMapper.getRecommend(userId);
        List<Long> followList = userAttentionMapper.getUserAttentionList(userId);
        List<FollowAttListVO> followAttListVOList = new ArrayList<>();
        for (User user : userList){
            if (followList.contains(user.getId())){
                continue;
            }else {
                FollowAttListVO followAttListVO = new FollowAttListVO();
                followAttListVO.setFollow(false);
                followAttListVO.setIntroduce(user.getIntroduce());
                followAttListVO.setName(user.getName());
                followAttListVO.setUserId(user.getId());
                followAttListVO.setSchool(user.getSchool());
                followAttListVO.setAvatar(user.getAvatar());
                followAttListVOList.add(followAttListVO);
            }
        }
        return followAttListVOList;
    }

    private void updateUserByToken(Long userId, String token){
        User user = userMapper.selectByPrimaryKey(userId);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        try {
            operations.set(token, user);
            logger.info("将用户信息存入redis成功！ token = "+token+"user = "+user.toString());
        }catch (Exception e){
            logger.error("将用户信息存入redis失败 "+e.getMessage());
        }
    }
}
