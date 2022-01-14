package com.tang.controller;

import com.tang.dao.user.UserDao;
import com.tang.entity.User;
import com.tang.response.RestResponse;
import com.tang.valid.AddValid;
import com.tang.valid.UpdateValid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public RestResponse<List<User>> query(User user) {
        final List<User> users = userDao.queryAll(user);
        return new RestResponse<>(users);
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public RestResponse<Integer> addUser(@Validated({AddValid.class}) User user) {
        return insertUser(user);
    }

    @PostMapping("update")
    @ApiOperation("更新用户")
    public RestResponse<Integer> updateUser(@Validated({UpdateValid.class}) User user) {
        return new RestResponse<>(userDao.update(user));
    }

    /**
     * 参数为list的时候，必须带有@RequestParam注解，而且前台传递的不能为空
     *
     * @param ids id列表
     * @return 影响行数
     */
    @PostMapping("delete")
    @ApiOperation("根据id列表删除用户")
    public RestResponse<Integer> deleteUser(@RequestParam("ids") List<Integer> ids) {
        Integer result = ids.stream().map(id -> userDao.deleteById(id)).reduce(Integer::sum).orElse(0);
        return new RestResponse<>(result);
    }

    @PostMapping("user")
    @ApiOperation("添加用户")
    public RestResponse<Integer> addUserJson(@Validated({AddValid.class}) @RequestBody User user) {
        return insertUser(user);
    }

    private RestResponse<Integer> insertUser(User user) {
        int result = userDao.insert(user);
        return new RestResponse<>(result);
    }
}