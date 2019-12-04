package com.study.springboot.rabbit.service;

import com.study.springboot.user.entity.User;

public interface RabbitService {

    void sendUser(User user);

}
