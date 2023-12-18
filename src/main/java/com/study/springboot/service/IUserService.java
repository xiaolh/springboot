package com.study.springboot.service;

import com.study.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaolh
 * @since 2019-12-02
 */
public interface IUserService extends IService<User> {

    User getUserById(Long id);

    /** 添加用户 */
    Integer addUser(User user);

    /**
     * 根据用户名密码获取用户
     * @param user
     * @return
     */
    User getUserByUsernamePassword(User user);

}
