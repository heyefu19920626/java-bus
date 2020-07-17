package com.heyefu.user.controller;

import com.heyefu.common.Common;
import com.heyefu.common.CommonDao;
import com.heyefu.user.dao.UserDao;
import com.heyefu.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang
 * Create in: 2020-07-12
 * Time: 下午3:24
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserDao userDao;

    @Resource
    CommonDao commonDao;

    @GetMapping("add")
    @ResponseBody
    @ApiOperation("添加用户")
    public User addUser(User user) {
        userDao.insert(user);
        return user;
    }

    @GetMapping("all")
    @ApiOperation("获取所有用户")
    public List<User> getUser() {
        return userDao.selectAll();
    }

    @GetMapping("commons")
    @ApiOperation("获取所有common")
    public List<Common> getCommons() {
        return commonDao.selectAll();
    }
}