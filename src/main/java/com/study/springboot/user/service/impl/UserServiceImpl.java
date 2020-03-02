package com.study.springboot.user.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.user.entity.User;
import com.study.springboot.user.mapper.UserMapper;
import com.study.springboot.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    UserMapper userMapper;

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

    @Cacheable
    public User getUserById(Integer id){
        return userMapper.selectById(id);
    }

    //@Scheduled(fixedRate = 20 * 60 * 1000)
    public void sdfasdf(){
        for (int i = 0;i < 200;i++){
            log.info("info");
            log.error("error");
        }
    }

}
