package com.tang.controller;

import com.tang.dao.user.UserDao;
import com.tang.entity.User;
import com.tang.response.RestResponse;
import com.tang.valid.AddValid;
import com.tang.valid.UpdateValid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang
 * @since 2020-11.15-17:11
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("user")
public class UserController {
    @Resource
    UserDao userDao;

    @GetMapping("list")
    public RestResponse<List<User>> query() {
        final List<User> users = userDao.queryAll(null);
        return new RestResponse<>(users);
    }

    @GetMapping("user")
    @ApiOperation("添加用户")
    public RestResponse<Integer> addUser(@Validated({AddValid.class}) User user) {
        int result = userDao.insert(user);
        return new RestResponse<>(result);
    }

    @PostMapping("update")
    @ApiOperation("更新用户")
    public RestResponse<Integer> updateUser(@Validated({UpdateValid.class}) User user) {
        return new RestResponse<>(userDao.update(user));
    }

    @PostMapping("user")
    @ApiOperation("添加用户")
    public RestResponse<Integer> addUserJson(@RequestBody User user) {
        int result = userDao.insert(user);
        return new RestResponse<>(result);
    }
}