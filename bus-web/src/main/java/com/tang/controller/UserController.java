package com.tang.controller;

import com.tang.dao.user.UserDao;
import com.tang.entity.User;
import com.tang.response.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang
 * @since 2020-11.15-17:11
 */
@RestController
public class UserController {
    @Resource
    UserDao userDao;

    @GetMapping("users")
    public RestResponse<List<User>> query() {
        final List<User> users = userDao.queryAll(null);
        return new RestResponse<>(users);
    }

    @GetMapping("user")
    public RestResponse<Integer> addUser(User user) {
        int result = userDao.insert(user);
        return new RestResponse<>(result);
    }

    @PostMapping("user")
    public RestResponse<Integer> addUserJson(@RequestBody User user) {
        int result = userDao.insert(user);
        return new RestResponse<>(result);
    }
}