package com.study.springboot.user.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.basic.bean.Error;
import com.study.springboot.basic.bean.UniversalException;
import com.study.springboot.user.entity.User;
import com.study.springboot.user.mapper.UserMapper;
import com.study.springboot.user.service.IUserService;
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

    //@Scheduled(fixedRate = 20 * 60 * 1000)
    public void testWtf(){
        User user = new User();
        user.setIsTrash(false);
        user.setUserName("god_xlh");
        user.setPassword("123456");
        user.setEmail("acexlh@live.com");
        user.setCreateAt(new DateTime());
        userMapper.insert(user);
    }

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

}
