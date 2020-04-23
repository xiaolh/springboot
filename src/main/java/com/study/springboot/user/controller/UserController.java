package com.study.springboot.user.controller;


import com.study.springboot.basic.entity.ResVo;
import com.study.springboot.pattern.entity.Result;
import com.study.springboot.user.entity.User;
import com.study.springboot.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaolh
 * @since 2019-12-02
 */
@Api(tags = "用户管理")
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "id",value = "用户 ID",dataType = "Integer",paramType = "path")
    @GetMapping(value = "find/{id}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResVo getUserById(@PathVariable Long id){
        return ResVo.success(userService.getUserById(id));
    }

    /**
     * ]通往登陆页面
     * @return 登陆页面
     */
    @GetMapping("login")
    public String toLogin(){
        return "login";
    }

    /**
     * 登陆方法
     * @return 登录结果
     */
    @PostMapping("login")
    @ResponseBody
    public Result login(HttpSession session, User u){

        User user = userService.getUserByUsernamePassword(u);
        if(user!=null){
            session.setAttribute("username",user.getUsername());
            return Result.ok();
        }
        return Result.fail("用户名或者密码错误！");
    }

    /**
     * 通往注册页面
     * @return 视图地址
     */
    @GetMapping("regist")
    public String toRegist(){
        return "regist";
    }

    /**
     * 注册方法
     * @param user 注册信息
     * @return 注册结果
     */
    @PostMapping("regist")
    @ResponseBody
    public Result regist(User user){

        Integer success = userService.addUser(user);
        if (success == 1){
            return Result.ok();
        }else {
            return Result.fail("注册失败！");
        }
    }

    /**
     * 注销方法
     * @return WEB 首页
     */
    @GetMapping("logout")
    public String logout(HttpSession session){

        session.removeAttribute("username");
        return "redirect:/";
    }

}

