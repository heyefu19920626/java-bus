package com.heyefu.user.entity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午1:56
 **/
@Controller
public class User {

    @GetMapping("user")
    @ResponseBody
    public Object getUser() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "tang");
        map.put("password", "123456");
        return map;
    }
}
