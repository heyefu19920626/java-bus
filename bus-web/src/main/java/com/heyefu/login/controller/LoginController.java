package com.heyefu.login.controller;

import com.heyefu.annotation.ServiceLog;
import com.heyefu.error.ErrorCode;
import com.heyefu.response.RestCode;
import com.heyefu.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午8:46
 **/
@Controller
@Slf4j
@Api(tags = "登录管理")
public class LoginController {
    @Resource
    HttpServletRequest request;

    @GetMapping("login")
    @ApiOperation("用户登录")
    @ResponseBody
    @ServiceLog(modules = "login",description = "user login")
    public RestCode login(User user) {
        Map<String, Object> result = new HashMap<>();
        if (user != null && "123456".equals(user.getPassword())) {
            request.getSession().setAttribute("user", user);
            result.put("status", true);
            return new RestCode(ErrorCode.SUCCESS, null, result);
        }
        result.put("status", false);
        return new RestCode(ErrorCode.FAIL, "用户名或密码错误", result);
    }

    @GetMapping("logout")
    @ApiOperation("用户注销")
    @ResponseBody
    public RestCode logout() {
        request.getSession().removeAttribute("user");
        Map<String, Object> result = new HashMap<>(1);
        result.put("status", true);
        return new RestCode(ErrorCode.SUCCESS, null, result);
    }
}