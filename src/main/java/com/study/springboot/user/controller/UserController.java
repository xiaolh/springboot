package com.study.springboot.user.controller;


import com.study.springboot.basic.bean.ResVo;
import com.study.springboot.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaolh
 * @since 2019-12-02
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("find/{id}")
    public ResVo getUserById(@PathVariable("id") Long id){
        return ResVo.success(userService.getUserById(id));
    }

}

