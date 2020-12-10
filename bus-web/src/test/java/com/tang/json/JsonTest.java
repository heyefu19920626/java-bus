package com.tang.json;

import com.alibaba.fastjson.JSON;
import com.tang.entity.User;
import com.tang.enums.UserType;
import org.junit.Test;

/**
 * @author he
 * @since 2020-12.10-23:25
 */
public class JsonTest {
    @Test
    public void test_fastjson() {
        User user = new User();
        user.setUserType(UserType.ADMIN);
        user.setName("name");
        user.setPwd("pwd");
        final String json = JSON.toJSONString(user);
        System.out.println(json);
        User target = JSON.parseObject(json, User.class);
        System.out.println(target);
    }
}
