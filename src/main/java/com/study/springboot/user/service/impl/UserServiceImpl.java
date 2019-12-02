package com.study.springboot.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springboot.user.entity.User;
import com.study.springboot.user.mapper.UserMapper;
import com.study.springboot.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaolh
 * @since 2019-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    //@Scheduled(fixedRate = 20 * 60 * 1000)
    public void testWtf(){
        User user = new User();
        user.setIsTrash(false);
        user.setUserName("acexlh");
        user.setPassword("123456");
        user.setEmail("acexlh@live.com");
        user.setCreateAt(new Date());
        userMapper.insert(user);
    }

}
