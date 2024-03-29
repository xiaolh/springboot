package com.study.springboot.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.entity.Error;
import com.study.springboot.entity.UniversalException;
import com.study.springboot.entity.User;
import com.study.springboot.mapper.UserMapper;
import com.study.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolh
 * @since 2019-12-02
 */
@Service
@CacheConfig(cacheNames = "user")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    //@Cacheable
    @Override
    public User getUserById(Long id){
        switch (RandomUtil.randomInt(3)){
            case 1:
            throw new RuntimeException();
            case 2:
            throw new UniversalException(Error.FAIL);
        }
        User user = userMapper.selectById(id);
        if (user == null){
            throw new UniversalException(Error.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public Integer addUser(User user) {
        int success = userMapper.insert(user);
        return success;
    }

    @Override
    public User getUserByUsernamePassword(User user) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
    }

}
