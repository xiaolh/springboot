package com.study.springboot.service;

import com.study.springboot.entity.User;

public interface RabbitService {

    void sendUser(User user);

}
