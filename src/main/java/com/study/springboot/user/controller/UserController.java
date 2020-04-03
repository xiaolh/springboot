package com.study.springboot.user.controller;


import com.study.springboot.basic.bean.ResVo;
import com.study.springboot.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "id",value = "用户 ID",dataType = "Integer",paramType = "path")
    @GetMapping("find/{id}")
    public ResVo getUserById(@PathVariable Long id){
        return ResVo.success(userService.getUserById(id));
    }

}

