package com.study.springboot;

import com.study.springboot.entity.User;
import com.study.springboot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //@Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        log.info("user-{}",user.toString());
    }

}
