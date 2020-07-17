package com.heyefu.user.controller;

import com.heyefu.user.dao.UserDao;
import com.heyefu.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午3:24
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserDao userDao;

    @GetMapping("add")
    @ResponseBody
    public User addUser(User user) {
        userDao.insert(user);
        return user;
    }

    @GetMapping("get")
    @ApiOperation("获取用户")
    @ApiImplicitParam(name = "name", value = "用户名", defaultValue = "tang")
    public User getUser(String name) {
        return User.builder().name(StringUtils.isBlank(name) ? "tang" : name).password("123456").build();
    }
}